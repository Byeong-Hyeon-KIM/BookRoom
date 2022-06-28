package com.bookroom.mypage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bookroom.book.vo.BookVo;
import com.bookroom.cart.vo.CartVo;
import com.bookroom.mypage.service.MypageService;
import com.bookroom.mypage.vo.MypageVo;
import com.bookroom.search.service.SearchService;

@Controller
@RequestMapping("/Mypage")
public class MypageController {

	@Autowired
	private MypageService mypageService;
	@Autowired
	private SearchService searchService;

	// 장바구니 이동
	@RequestMapping("/Cart")
	public ModelAndView cart(HttpSession session) {

		ModelAndView mv = new ModelAndView();

		if (session.getAttribute("userid") == null) {
			System.out.println("session is null");
			mv.setViewName("redirect:/Login");
		} else {

			String user_id = (String) session.getAttribute("userid");
			List<CartVo> userCart = mypageService.cartView(user_id);

			BookVo book = new BookVo();
			String bookName = "";
			String bookImg = "";
			String auth = "";
			int bookPrice = 0;
			String publisher = "";
			int size = userCart.size();

			for (int i = 0; i < userCart.size(); i++) {
				String isbn = userCart.get(i).getIsbn();
				book = searchService.searchBookByISBN(isbn);
				bookName = book.getTitle();
				bookImg = book.getImage();
				bookPrice = Integer.parseInt(book.getPrice());
				auth = book.getAuthor();
				publisher = book.getPublisher();

				userCart.get(i).setBookName(bookName);
				userCart.get(i).setImg(bookImg);
				userCart.get(i).setPrice(bookPrice);
				userCart.get(i).setAuth(auth);
				userCart.get(i).setPublisher(publisher);
			}
			mv.addObject("size", size);
			mv.addObject("userCart", userCart);
			mv.setViewName("mypage/cart");
		}
		return mv;
	}

	// 장바구니 담기
	@RequestMapping("/InsertCart")
	public ModelAndView insertCart(HttpSession session, @RequestParam HashMap<String, Object> map) {

		ModelAndView mv = new ModelAndView();

		if (session.getAttribute("userid") == null) {
			System.out.println("session is null");
			mv.setViewName("redirect:/Login");
		} else {
			map.put("userid", session.getAttribute("userid"));
			mypageService.insertCart(map);
			mv.setViewName("redirect:/Mypage/Cart");
		}

		return mv;
	}

	// 주문내역
	@RequestMapping("/OrderList")
	public ModelAndView orderedList(HttpSession session, @RequestParam HashMap<String, Object> map) {

		ModelAndView mv = new ModelAndView();
		System.out.println(map);

		if (session.getAttribute("userid") == null) {
			System.out.println("session is null");
			mv.setViewName("redirect:/Login");
		} else {
			String userid = (String) session.getAttribute("userid");

			// 전체조회
			if (map.isEmpty()) {
				List<MypageVo> list = mypageService.getOrderList(userid);
				mv.addObject("list", list);
				mv.setViewName("/mypage/orderlist");

				// 특정기간 조회
			} else {
				List<MypageVo> list = mypageService.getOrderListByPeriod(map, userid);
				mv.addObject("list", list);
				mv.setViewName("/mypage/orderlist");
				return mv;
			}
		}
		return mv;
	}

	// 장바구니 삭제
	@RequestMapping("/Delete")
	public ModelAndView deleteCart(HttpSession session, @RequestParam HashMap<String, Object> map) {

		ModelAndView mv = new ModelAndView();

		String userid = (String) session.getAttribute("userid");
		;
		/* int userCart = */mypageService.deleteCart(map, userid);
		mv.setViewName("redirect:/Mypage/Cart");

		return mv;
	}

	@RequestMapping("/Pay")
	public ModelAndView pay(HttpSession session, @RequestParam(value = "isbn") String[] isbnList,
			@RequestParam(value = "quantity") String[] quantityList, String cart) {

		ModelAndView mv = new ModelAndView();

		if (session.getAttribute("userid") == null) {
			System.out.println("session is null");
			mv.setViewName("redirect:/Login");
			return mv;
		}

		List<BookVo> bookList = new ArrayList<BookVo>();
		int totalPrice = 0;

		for (int i = 0; i < isbnList.length; i++) {
			BookVo book = searchService.searchBookByISBN(isbnList[i]);
			bookList.add(book);

			totalPrice += Integer.parseInt(book.getPrice()) * Integer.parseInt(quantityList[i]);
		}

		mv.addObject("bookList", bookList);
		mv.addObject("quantityList", quantityList);
		mv.addObject("totalPrice", totalPrice);
		if (cart != null) {
			if (!cart.equals("")) {
				mv.addObject("cart", cart);
			}
		}

		mv.setViewName("/mypage/pay");

		return mv;
	}

	@RequestMapping("/PayComplete")
	public ModelAndView payComplete(HttpSession session, String zipcode, String address, String payment, String[] isbn,
			String[] quantity, String cart) {

		ModelAndView mv = new ModelAndView();

		mypageService.insertOrder((String) session.getAttribute("userid"), zipcode, address, payment, isbn, quantity);
		if (cart != null) {
			if (!cart.equals("")) {
				mypageService.deleteCartAll((String) session.getAttribute("userid"));
			}
		}

		mv.setViewName("redirect:/");
		return mv;
	}

}

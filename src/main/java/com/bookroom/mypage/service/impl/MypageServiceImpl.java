package com.bookroom.mypage.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookroom.book.vo.BookVo;
import com.bookroom.cart.vo.CartVo;
import com.bookroom.mypage.dao.MypageDao;
import com.bookroom.mypage.service.MypageService;
import com.bookroom.mypage.vo.MypageVo;
import com.bookroom.search.service.impl.SearchServiceImpl;

@Service
public class MypageServiceImpl implements MypageService {

	@Autowired
	private MypageDao dao;

	@Override
	public List<MypageVo> getOrderList(String userid) {

		BookVo book = new BookVo();
		SearchServiceImpl searchService = new SearchServiceImpl();
		List<MypageVo> getOrderList = dao.getOrderList(userid);
		String bookName = "";

		for (int i = 0; i < getOrderList.size(); i++) {
			String isbn = getOrderList.get(i).getIsbn();
			book = searchService.searchBookByISBN(isbn);
			bookName = book.getTitle();
			getOrderList.get(i).setBookName(bookName);
		}
		return getOrderList;
	}

	@Override
	public List<MypageVo> getOrderListByPeriod(HashMap<String, Object> map, String userid) {
		BookVo book = new BookVo();
		SearchServiceImpl searchService = new SearchServiceImpl();
		List<MypageVo> getOrderListByPeriod = dao.getOrderListByPeriod(map, userid);
		String bookName = "";

		for (int i = 0; i < getOrderListByPeriod.size(); i++) {
			String isbn = getOrderListByPeriod.get(i).getIsbn();
			book = searchService.searchBookByISBN(isbn);
			bookName = book.getTitle();
			getOrderListByPeriod.get(i).setBookName(bookName);
		}
		return getOrderListByPeriod;
	}

	@Override
	public List<CartVo> cartView(String UserNum) {
		List<CartVo> cartView = dao.userView(UserNum);

		return cartView;
	}

	@Override
	public void insertCart(HashMap<String, Object> map) {
		dao.insertCart(map);
	}

	@Override
	public int deleteCart(HashMap<String, Object> map, String userid) {

		int deleteCart = dao.deleteCart(map, userid);

		return deleteCart;
	}

	@Override
	public void insertOrder(String userid, String zipcode, String address, String payment, String[] isbn,
			String[] quantity) {
		dao.insertOrder(userid, zipcode, address, payment, isbn, quantity);
	}

	@Override
	public void deleteCartAll(String userid) {
		dao.deleteCartAll(userid);
	}
}
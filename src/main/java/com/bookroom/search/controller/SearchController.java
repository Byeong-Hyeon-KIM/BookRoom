package com.bookroom.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bookroom.book.vo.BookVo;
import com.bookroom.search.service.SearchService;
import com.bookroom.search.vo.SearchVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Controller
@RequestMapping("/Search")
public class SearchController {

	@Autowired
	private SearchService searchService;

	// 검색
	@RequestMapping
	public ModelAndView search(String keyword, String searchOption, String sortOption, String display, String pageNum)
			throws JsonMappingException, JsonProcessingException {

		if (sortOption == null) {
			sortOption = "sim";
		}
		if (display == null) {
			display = "10";
		}
		if (pageNum == null) {
			pageNum = "1";
		}

		ModelAndView mv = new ModelAndView();

		SearchVo searchVo = searchService.searchBooks(keyword, searchOption, sortOption, display, pageNum);

		mv.addObject("keyword", keyword);
		mv.addObject("searchOption", searchOption);
		mv.addObject("sortOption", sortOption);
		mv.addObject("display", searchVo.getDisplay());
		mv.addObject("pageNum", pageNum);
		mv.addObject("total", searchVo.getTotal());
		mv.addObject("bookList", searchVo.getItems());

		mv.setViewName("search/searchList");
		return mv;
	}

	// 책정보 가져오기(isbn)
	@RequestMapping("/Prod")
	public ModelAndView prod(String isbn) {
		ModelAndView mv = new ModelAndView();
		
		// isbn에 해당되는 책 정보 가져오기
		BookVo book = searchService.searchBookByISBN(isbn);
		
		mv.addObject("book", book);

		mv.setViewName("search/view");
		return mv;
	}
	
}

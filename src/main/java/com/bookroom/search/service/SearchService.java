package com.bookroom.search.service;

import com.bookroom.book.vo.BookVo;
import com.bookroom.search.vo.SearchVo;

public interface SearchService {

	SearchVo searchBooks(String keyword, String searchOption, String sortOption, String display, String pageNum);
	
	BookVo searchBookByISBN(String isbn);
}

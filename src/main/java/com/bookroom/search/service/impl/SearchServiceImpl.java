package com.bookroom.search.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bookroom.book.vo.BookVo;
import com.bookroom.search.service.SearchService;
import com.bookroom.search.vo.SearchVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service("searchService")
public class SearchServiceImpl implements SearchService {

	private static String clientId = "4MDbN8Ir8Kag_07nQL_n";
	private static String clientSecret = "7rpbPZc0Kx";

	@Override
	public SearchVo searchBooks(String keyword, String searchOption, String sortOption, String display,
			String pageNum) {

		String query = null;
		try {
			query = URLEncoder.encode(keyword, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String apiURL = null;

		// 검색옵션 + 검색어
//		System.out.println("ServiceImpl() Search()= " + "검색어=" + keyword + ", 검색옵션=" + searchOption + ", 정렬옵션="
//				+ sortOption + ", 보여줄 갯수=" + display);

		switch (searchOption) {
		case "all":
			apiURL = "https://openapi.naver.com/v1/search/book?query=" + query;
			break;
		case "title":
			apiURL = "https://openapi.naver.com/v1/search/book_adv?d_titl=" + query;
			break;
		case "auth":
			apiURL = "https://openapi.naver.com/v1/search/book_adv?d_auth=" + query;
			break;
		case "catg":
			apiURL = "https://openapi.naver.com/v1/search/book_adv?d_cont=1&d_catg=" + query;
			break;
		}

		// 보여줄 개수
		apiURL += "&display=" + display;
		// 몇 번째 부터 출력, 첫 검색은 start 항상 1이다.
		int start = Integer.parseInt(display.trim()) * (Integer.parseInt(pageNum.trim()) - 1) + 1;
		apiURL += "&start=" + Integer.toString(start);
		// 정렬 옵션
		apiURL += "&sort=" + sortOption;

//		System.out.println("ServiceImpl() searchAPI Url= " + apiURL);

		Map<String, String> requestHeaders = new HashMap<String, String>();
		requestHeaders.put("X-Naver-Client-Id", clientId);
		requestHeaders.put("X-Naver-Client-Secret", clientSecret);
		requestHeaders.put("Content-Type", "application/json");

		// json response
		String responseBody = get(apiURL, requestHeaders);

		// json -> object
		ObjectMapper om = new ObjectMapper();
		SearchVo vo = null;
		try {
			vo = om.readValue(responseBody, SearchVo.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return vo;
	}

	@Override
	public BookVo searchBookByISBN(String isbn) {
		String query = null;
		try {
			query = URLEncoder.encode(isbn, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String apiURL = "https://openapi.naver.com/v1/search/book_adv?d_isbn=" + query;
		// 보여줄 개수

//		System.out.println("ServiceImpl() searchAPI Url= " + apiURL);

		Map<String, String> requestHeaders = new HashMap<String, String>();
		requestHeaders.put("X-Naver-Client-Id", clientId);
		requestHeaders.put("X-Naver-Client-Secret", clientSecret);
		requestHeaders.put("Content-Type", "application/json");

		// json response
		String responseBody = get(apiURL, requestHeaders);
//		System.out.println(responseBody);
		// json -> object
		ObjectMapper om = new ObjectMapper();
		SearchVo vo = null;
		try {
			vo = om.readValue(responseBody, SearchVo.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return vo.getItems().get(0);
	}

	// ----------------------------------------------------------------------------------------------------------
	private static String get(String apiUrl, Map<String, String> requestHeaders) {
		HttpURLConnection con = connect(apiUrl);
		try {
			con.setRequestMethod("GET");
			for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
				con.setRequestProperty(header.getKey(), header.getValue());
			}

			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
				return readBody(con.getInputStream());
			} else { // 에러 발생
				return readBody(con.getErrorStream());
			}
		} catch (IOException e) {
			throw new RuntimeException("API 요청과 응답 실패", e);
		} finally {
			con.disconnect();
		}
	}

	private static HttpURLConnection connect(String apiUrl) {
		try {
			URL url = new URL(apiUrl);
			return (HttpURLConnection) url.openConnection();
		} catch (MalformedURLException e) {
			throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
		} catch (IOException e) {
			throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
		}
	}

	private static String readBody(InputStream body) {
		InputStreamReader streamReader = new InputStreamReader(body);

		try (BufferedReader lineReader = new BufferedReader(streamReader)) {
			StringBuilder responseBody = new StringBuilder();

			String line;
			while ((line = lineReader.readLine()) != null) {
				responseBody.append(line);
			}

			return responseBody.toString();
		} catch (IOException e) {
			throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
		}
	}

}

package com.bookroom.mypage.dao;

import java.util.HashMap;
import java.util.List;

import com.bookroom.cart.vo.CartVo;
import com.bookroom.mypage.vo.MypageVo;

public interface MypageDao {

	List<MypageVo> getOrderList(String userid);

	List<MypageVo> getOrderListByPeriod(HashMap<String, Object> map, String userid);

	List<CartVo> userView(String userNum);

	void insertCart(HashMap<String, Object> map);

	int deleteCart(HashMap<String, Object> map, String userid);
	
	void deleteCartAll(String userid);

	void insertOrder(String userid, String zipcode, String address, String payment, String[] isbn, String[] quantity);
}

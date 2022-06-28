package com.bookroom.mypage.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookroom.cart.vo.CartVo;
import com.bookroom.mypage.dao.MypageDao;
import com.bookroom.mypage.vo.MypageVo;

@Repository
public class MypageDaoImpl implements MypageDao {

	@Autowired
	private SqlSession sqlSession;

	// 주문조회
	@Override
	public List<MypageVo> getOrderList(String userid) {
		List<MypageVo> getOrderList = sqlSession.selectList("OrderList.GetOrderList", userid);

		return getOrderList;
	}

	@Override
	public List<MypageVo> getOrderListByPeriod(HashMap<String, Object> map, String userid) {

		System.out.println("적용됨");
		map.put("userid", userid);
		List<MypageVo> list = sqlSession.selectList("OrderList.getOrderListByPeriod", map);
		System.out.println("다오 리스트:" + list);
		return list;
	}

	@Override
	public List<CartVo> userView(String UserNum) {
		List<CartVo> userView = sqlSession.selectList("User.UserSelect", UserNum);
		return userView;
	}

	@Override
	public void insertCart(HashMap<String, Object> map) {
		sqlSession.insert("OrderList.InsertCart", map);
	}

	@Override
	public int deleteCart(HashMap<String, Object> map, String userid) {
		map.put("userid", userid);
		System.out.println("DaoImpl() map=" + map);
		int deleteCart = sqlSession.delete("User.deleteCart", map);
		return deleteCart;
	}

	@Override
	public void insertOrder(String userid, String zipcode, String address, String payment, String[] isbn,
			String[] quantity) {
		Map<String, Object> orderMap = new HashMap<String, Object>();
		orderMap.put("userid", userid);
		orderMap.put("zipcode", zipcode);
		orderMap.put("address", address);
		orderMap.put("payment", payment);
		
		sqlSession.insert("OrderList.insertOrder2", orderMap);
		for(int i = 0; i < isbn.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("isbn", isbn[i]);
			map.put("quantity", quantity[i]);
			sqlSession.insert("OrderList.insertOrderList", map);
		}
	}

	@Override
	public void deleteCartAll(String userid) {
		sqlSession.delete("User.deleteCartAll", userid);
	}

}
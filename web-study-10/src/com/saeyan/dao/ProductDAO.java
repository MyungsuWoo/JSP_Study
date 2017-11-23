package com.saeyan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.saeyan.dto.ProductVO;

import util.DBManager;

public class ProductDAO {
	private ProductDAO() {
	}
	
	private static ProductDAO instance = new ProductDAO();
	
	public static ProductDAO getInstance() {
		return instance;
	}
	
	// c Read u d
	public List<ProductVO> selectAllProduct() {
		// 최근 등록한 상품 먼저 출력하기
		String sql = "SELECT * FROM  PRODUCT ORDER BY CODE DESC";
		List<ProductVO> list = new ArrayList<ProductVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) { // 이동은 행(로우) 단위로
				ProductVO pVo = new ProductVO();
				pVo.setCode(rs.getInt("code"));
				pVo.setName(rs.getString("name"));
				pVo.setPrice(rs.getInt("price"));
				pVo.setPictureUrl(rs.getString("pictureUrl"));
				pVo.setDescription(rs.getString("description"));
				list.add(pVo);
			} // while문 끝
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		
		return list;
	} // selectAllProducts() {
	
	// Create r u d
	public void insertProduct(ProductVO pVo) {
		String sql = "INSERT INTO PRODUCT VALUES(PRODUCT_SEQ.NEXTVAL, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pVo.getName());
			pstmt.setInt(2, pVo.getPrice());
			pstmt.setString(3, pVo.getPictureUrl());
			pstmt.setString(4, pVo.getDescription());
			pstmt.executeUpdate(); // 실행
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
} // ProductDAO {
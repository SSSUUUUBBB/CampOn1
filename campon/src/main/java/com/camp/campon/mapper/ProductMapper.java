package com.camp.campon.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.camp.campon.dto.Product;

@Mapper
public interface ProductMapper {
    //상품 하나 선택
    public Product select(int productNo) throws Exception;

    //카테고리별 상품목록
    public List<Product> getCategoryList(String category) throws Exception;
    
    // 상품 등록
    public int productInsert(Product product) throws Exception;
    //상세이미지 등록
    public int insertImgs(Product product) throws Exception;

    //상품 수정
    public int productUpdate(Product product) throws Exception;
     //상세이미지 삭제
    public int deleteImgs(int productNo) throws Exception;
    
    //상품번호 최댓값
    public int maxPk() throws Exception;

    // 찜 목록
    public List<Product> wishList();

    // 찜 삭제
    public int wishListDelete(int wishlistNo);

    // 장바구니 목록
    public List<Product> cartList();

    // 장바구니 삭제
    public int cartListDelete(int cartlistNo);
    
}

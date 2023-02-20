package ymw.delivery.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class OrderDetail {
	private String orderNum;
	private String foodInfoJSON;
}
//	private int storeAddress1;
//	private String storeAddress2;
//	private String storeAddress3;
//	int totalprice;
//	String phone;
//	int storeId;
//	int userId;
//	String paymethod;
//	int usedpoint;
//	String request;

//	private long id;
//	private int category;
//	private String storeName;
//	private String storePhone;
//	private String storeImg;
//	private String storeThumb;
//	private int openingTime;
//	private int closingTime;
//	private int minDelevery;
//	private int deleveryTime;
//	private int deleveryTip;
//	private String storeDes;
//
//INSERT INTO
//
//	BM_ORDER_USER (
//	int    ,STORE_ID 
//	 int   ,USER_ID 
//	String    ,PAY_METHOD 
//	   int ,USED_POINT 
//	   String ,REQUEST 
//   ) VALUES (
//	    ${orderNum }
//	    ,#{storeId }
//	    ,#{userId }
//	    ,#{payMethod }
//	    ,#{phone }  
//	    ,#{deleveryAddress1 }
//	    ,#{deleveryAddress2 }
//	    ,#{deleveryAddress3 } 
//	    ,#{totalPrice } 
//	    ,#{usedPoint } 
//	    ,#{request } 
//   )
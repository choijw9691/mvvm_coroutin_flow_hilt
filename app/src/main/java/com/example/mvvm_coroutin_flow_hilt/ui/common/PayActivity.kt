package com.example.mvvm_coroutin_flow_hilt.ui.common

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvm_coroutin_flow_hilt.R
import kr.co.bootpay.android.Bootpay
import kr.co.bootpay.android.events.BootpayEventListener
import kr.co.bootpay.android.models.BootExtra
import kr.co.bootpay.android.models.BootItem
import kr.co.bootpay.android.models.BootUser
import kr.co.bootpay.android.models.Payload

class PayActivity: AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay)

        val user: BootUser = BootUser().setPhone("010-1234-5678") // 구매자 정보
        val extra: BootExtra = BootExtra()
            .setCardQuota("0,2,3") // 일시불, 2개월, 3개월 할부 허용, 할부는 최대 12개월까지 사용됨 (5만원 이상 구매시 할부허용 범위)
        val price = 1000.0
        val pg = "나이스페이"
        val method = "네이버페이"

        //통계용 데이터 추가
        val items: MutableList<BootItem> = ArrayList()
        val item1: BootItem =
            BootItem().setName("마우스").setId("ITEM_CODE_MOUSE").setQty(1).setPrice(500.0)
        val item2: BootItem =
            BootItem().setName("키보드").setId("ITEM_KEYBOARD_MOUSE").setQty(1).setPrice(500.0)
        items.add(item1)
        items.add(item2)
        val payload = Payload()
        payload.setApplicationId("62fc88bfcf9f6d001a17a087")
            /*필수*/   .setOrderName("부트페이 결제테스트")
             //.setPg(pg) //결제 요청할 PG Symbol을 입력합니다.( ex) 다날, danal, 케이씨피, KCP )
            /*필수*/   .setOrderId("1234")
            // .setMethod(method) //결제 요청할 결제수단 symbol을 입력합니다.( ex) 카드, card, 휴대폰, phone )
            /*필수*/ .setPrice(price)
           .setUser(getBootUser()) //구매자 정보(이름, 전화번호, 이메일, 주소)정보를 입력합니다. 일부 PG의 경우엔 사용자 일부 정보가 필수값으로 들어갑니다.
         .setExtra(extra).items=items //추가적인 결제 옵션 정보를 입력합니다.
         // .setItems(items) //판매하는 상품 정보 내역입니다. 일부 PG에서는 에스크로 결제시 필수값입니다. 상품의총 개수 * 금액의 합계가 결제금액(price)과 맞지 않으면 오류가 발생합니다.
       // .setUserToken() 간편결제시 필요한 token 값입니다.  항목을 참고해주세요.
        val map: MutableMap<String, Any> = HashMap()
        map["1"] = "abcdef"
        map["2"] = "abcdef55"
        map["3"] = 1234
        payload.setMetadata(map)
        Bootpay.init(this,this)
            .setPayload(payload)
            .setEventListener(object : BootpayEventListener {
                override fun onCancel(data: String?) {
                    Log.d("JIWOUNG","cancel: "+ data.toString())
                }

                override fun onError(data: String?) {
                    Log.d("JIWOUNG","onError: "+ data.toString())
                }

                override fun onClose(data: String?) {
                    Log.d("JIWOUNG", "onClose: "+data.toString())
                    Bootpay.removePaymentWindow()
                }

                override fun onIssued(data: String?) { //가상계좌 발급이 완료되면 호출되는 함수
                    Log.d("JIWOUNG","onIssued: "+ data.toString())
                }

                override fun onConfirm(data: String?): Boolean {
                    Log.d("JIWOUNG", "onConfirm: "+data.toString())
                    //  Bootpay.transactionConfirm(data);
                    // 재고가 있어서 결제를 진행하려 할때 true (방법 1)
                    return true //재고가 있어서 결제를 진행하려 할때 true (방법 2)
                    //   return false; //결제를 진행하지 않을때 false
                }

                override fun onDone(data: String?) {
                    Log.d("JIWOUNG","onDone: "+ data.toString())
                }
            }).requestPayment()
    }
    fun getBootUser(): BootUser? {
        val userId = "123411aaaaaaaaaaaabd4ss121"
        val user = BootUser()
        user.id = userId
        user.area = "서울"
        user.gender = 1 //1: 남자, 0: 여자
        user.email = "test1234@gmail.com"
        user.phone = "010-1234-4567"
        user.birth = "1988-06-10"
        user.username = "홍길동"
        return user
    }
}
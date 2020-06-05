package com.satomi.service;

/**
 * @author nasazumi
 * @description
 * @date 2020-06-03
 */
import java.util.ArrayList;
import java.util.List;

import com.satomi.config.PaypalPaymentIntent;
import com.satomi.config.PaypalPaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

@Service
public class PaypalService {

    @Autowired
    private APIContext apiContext;

    /**
     *
     * @param total         总价格
     * @param currency      货币
     * @param method        付款方式
     * @param intent
     * @param description
     * @param cancelUrl     返回路径
     * @param successUrl    成功路径
     * @return
     * @throws PayPalRESTException
     */
    public Payment createPayment(
            Double total,
            String currency,
            PaypalPaymentMethod method,
            PaypalPaymentIntent intent,
            String description,
            String cancelUrl,
            String successUrl) throws PayPalRESTException{
        /**
         * Amount  extends PayPalModel
         * @currency    货币代码 PayPal并不支持所有的货币代码
         * @total       从付款人向收款人收取的总金额
         * @details     付款金额的其他详细信息。
         */
        Amount amount = new Amount();
        amount.setCurrency(currency);
        amount.setTotal(String.format("%.2f", total));
        /**
         * Transaction extends TransactionBase extends CartBase
         * 交易明细，包括金额和项目明细
         * @amount 收取的金额。
         * @description
         */
        Transaction transaction = new Transaction();
        transaction.setDescription(description);
        transaction.setAmount(amount);

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);
        /**
         * Payer extends PayPalModel
         * Payment method being used - PayPal Wallet payment, Bank Direct Debit  or Direct Credit card.
         */
        Payer payer = new Payer();
        payer.setPaymentMethod(method.toString());

        /**
         * @Intent 付款意向。
         */
        Payment payment = new Payment();
        payment.setIntent(intent.toString());
        payment.setPayer(payer);
        payment.setTransactions(transactions);
        /**
         * @returnUrl 批准付款后付款人将重定向到的网址。
         * @cancelUrl 取消付款后付款人将重定向到的网址
         */
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(cancelUrl);
        redirectUrls.setReturnUrl(successUrl);
        payment.setRedirectUrls(redirectUrls);

        /**
         * 创建处理新的付款资源。
         */
        return payment.create(apiContext);
    }

    /**
     * 当付款方式为PayPal时，执行与此付款关联的操作（在付款人批准后）。
     * @param paymentId
     * @param payerId
     * @return
     * @throws PayPalRESTException
     */
    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException{
        Payment payment = new Payment();
        payment.setId(paymentId);
        /**
         * @playerId 付款人的ID，由PayPal在“return_url”中传递。
         */
        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);
        return payment.execute(apiContext, paymentExecute);
    }
}

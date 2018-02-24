package io.nem.client.account;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import io.nem.client.account.response.*;

@Headers({"Accept: application/json"})
public interface FeignAccountClient extends AccountClient {
    @Override
    @RequestLine("GET /account/generate")
    KeyPair generate();

    @Override
    @RequestLine("GET /account/get?address={address}")
    AccountMetaDataPair getFromAddress(@Param("address") String address);

    @Override
    @RequestLine("GET /account/get/from-public-key?publicKey={publicKey}")
    AccountMetaDataPair getFromPublicKey(@Param("publicKey") String address);

    @Override
    @RequestLine("GET /account/get/forwarded?address={address}")
    AccountMetaDataPair getForwarded(@Param("address") String delegateAddress);

    @Override
    @RequestLine("GET /account/get/forwarded/from-public-key?publicKey={publicKey}")
    AccountMetaDataPair getForwardedFromPublicKey(@Param("publicKey") String publicKey);

    @Override
    @RequestLine("GET /account/status?address={address}")
    AccountMetaData status(@Param("address") String address);

    @Override
    @RequestLine("GET /account/transfers/incoming?address={address}")
    Transactions incomingTransactions(@Param("address") String address);

    @Override
    @RequestLine("GET /account/transfers/incoming?address={address}&id={id}")
    Transactions incomingTransactions(@Param("address") String address, @Param("id") long id);

    @Override
    @RequestLine("GET /account/transfers/outgoing?address={address}")
    Transactions outgoingTransactions(@Param("address") String address);

    @Override
    @RequestLine("GET /account/transfers/outgoing?address={address}&id={id}")
    Transactions outgoingTransactions(@Param("address") String address, @Param("id") long id);

    @Override
    @RequestLine("GET /account/transfers/all?address={address}")
    Transactions allTransactions(@Param("address") String address);

    @Override
    @RequestLine("GET /account/transfers/all?address={address}&id={id}")
    Transactions allTransactions(@Param("address") String address, @Param("id") long id);

    @Override
    @RequestLine("GET /account/unconfirmedTransactions?address={address}")
    UnconfirmedTransactions unconfirmedTransactions(@Param("address") String address);
}

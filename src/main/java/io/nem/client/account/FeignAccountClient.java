package io.nem.client.account;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import io.nem.client.account.request.AccountPrivateKeyTransactionsPage;
import io.nem.client.account.request.PrivateKey;
import io.nem.client.account.response.AccountMetaData;
import io.nem.client.account.response.AccountMetaDataPair;
import io.nem.client.account.response.KeyPair;
import io.nem.client.account.response.UnlockedInfo;
import io.nem.client.account.response.harvest.HarvestsResponse;
import io.nem.client.account.response.history.HistoryResponse;
import io.nem.client.account.response.importance.ImportanceResponse;
import io.nem.client.account.response.mosaic.MosaicsResponse;
import io.nem.client.account.response.mosaic.OwnedMosaicsResponse;
import io.nem.client.account.response.namespace.NamespacesResponse;
import io.nem.client.account.response.transaction.Transactions;
import io.nem.client.account.response.transaction.UnconfirmedTransactions;

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

    @Override
    @Headers("Content-Type: application/json")
    @RequestLine("POST /local/account/transfers/incoming")
    Transactions incomingDecodedTransactions(AccountPrivateKeyTransactionsPage request);

    @Override
    @Headers("Content-Type: application/json")
    @RequestLine("POST /local/account/transfers/outgoing")
    Transactions outgoingDecodedTransactions(AccountPrivateKeyTransactionsPage request);

    @Override
    @Headers("Content-Type: application/json")
    @RequestLine("POST /local/account/transfers/all")
    Transactions allDecodedTransactions(AccountPrivateKeyTransactionsPage request);

    @Override
    @RequestLine("GET /account/harvests?address={address}&hash={hash}")
    HarvestsResponse harvests(@Param("address") String address, @Param("hash") String hash);

    @Override
    @RequestLine("GET /account/importances")
    ImportanceResponse importances();

    @Override
    @RequestLine("GET /account/namespace/page?address={address}&parent={parent}&id={id}&pageSize={pageSize}")
    NamespacesResponse namespaces(@Param("address") String address, @Param("parent") String parent, @Param("id") Long id, @Param("pageSize") Integer pageSize);

    @Override
    @RequestLine("GET /account/mosaic/definition/page?address={address}&parent={parent}&id={id}")
    MosaicsResponse mosaics(@Param("address") String address, @Param("parent") String parent, @Param("id") Long id);

    @Override
    @RequestLine("GET /account/mosaic/owned?address={address}")
    OwnedMosaicsResponse ownedMosaics(@Param("address") String address);

    @Override
    @Headers("Content-Type: application/json")
    @RequestLine("POST /account/unlock")
    void unlock(PrivateKey privateKey);

    @Override
    @Headers("Content-Type: application/json")
    @RequestLine("POST /account/lock")
    void lock(PrivateKey privateKey);

    @Override
    @RequestLine("POST /account/unlocked/info")
    UnlockedInfo unlockedInfo();

    @Override
    @RequestLine("GET /account/historical/get?address={address}&startHeight={startHeight}&endHeight={endHeight}&increment={increment}")
    HistoryResponse history(@Param("address") String address, @Param("startHeight") long startHeight, @Param("endHeight") long endHeight, @Param("increment") int increment);

}

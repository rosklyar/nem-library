package io.nem.client.transaction;

import feign.Headers;
import feign.RequestLine;
import io.nem.client.transaction.domain.RequestAnnounce;
import io.nem.client.transaction.domain.NemAnnounceResult;

@Headers({"Accept: application/json"})
public interface FeignTransactionClient {

    @Headers("Content-Type: application/json")
    @RequestLine("POST /transaction/announce")
    NemAnnounceResult prepare(RequestAnnounce requestAnnounce);
}

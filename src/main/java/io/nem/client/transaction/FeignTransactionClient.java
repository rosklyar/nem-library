package io.nem.client.transaction;

import feign.Headers;
import feign.RequestLine;
import io.nem.client.transaction.request.RequestAnnounce;
import io.nem.client.transaction.response.NemAnnounceResult;

@Headers({"Accept: application/json"})
public interface FeignTransactionClient {

    @Headers("Content-Type: application/json")
    @RequestLine("POST /transaction/announce")
    NemAnnounceResult prepare(RequestAnnounce requestAnnounce);
}

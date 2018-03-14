package com.github.rosklyar.client.transaction;

import com.github.rosklyar.client.transaction.domain.NemAnnounceResult;
import com.github.rosklyar.client.transaction.domain.RequestAnnounce;
import feign.Headers;
import feign.RequestLine;

@Headers({"Accept: application/json"})
public interface FeignTransactionClient {

    @Headers("Content-Type: application/json")
    @RequestLine("POST /transaction/announce")
    NemAnnounceResult prepare(RequestAnnounce requestAnnounce);
}

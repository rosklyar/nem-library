package io.nem.client.mosaic;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import io.nem.client.mosaic.domain.MosaicsMetaDataResponse;
import io.nem.client.mosaic.domain.Namespace;
import io.nem.client.mosaic.domain.NamespacesMetaDataResponse;

@Headers({"Accept: application/json"})
public interface FeignMosaicClient extends MosaicClient {

    @Override
    @RequestLine("GET /namespace/root/page?id={id}&pageSize={pageSize}")
    NamespacesMetaDataResponse namespaces(@Param("id") Long id, @Param("pageSize") Integer pageSize);

    @Override
    @RequestLine("GET /namespace?namespace={id}")
    Namespace namespace(@Param("id") String id);

    @Override
    @RequestLine("GET /namespace/mosaic/definition/page?namespace={namespaceId}&id={id}&pageSize={pageSize}")
    MosaicsMetaDataResponse mosaics(@Param("namespaceId") String namespaceId, @Param("id") Long id, @Param("pageSize") Integer pageSize);
}

package com.github.rosklyar.client.mosaic;

import com.github.rosklyar.client.mosaic.domain.MosaicsMetaDataResponse;
import com.github.rosklyar.client.mosaic.domain.Namespace;
import com.github.rosklyar.client.mosaic.domain.NamespacesMetaDataResponse;

public interface MosaicClient {
    NamespacesMetaDataResponse namespaces(Long id, Integer pageSize);

    Namespace namespace(String id);

    MosaicsMetaDataResponse mosaics(String namespaceId, Long id, Integer pageSize);
}

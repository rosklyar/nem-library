package io.nem.client.mosaic;

import io.nem.client.mosaic.response.MosaicsMetaDataResponse;
import io.nem.client.mosaic.response.Namespace;
import io.nem.client.mosaic.response.NamespacesMetaDataResponse;

public interface MosaicClient {
    NamespacesMetaDataResponse namespaces(Long id, Integer pageSize);
    Namespace namespace(String id);
    MosaicsMetaDataResponse mosaics(String namespaceId, Long id, Integer pageSize);
}

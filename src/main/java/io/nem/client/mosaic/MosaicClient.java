package io.nem.client.mosaic;

import io.nem.client.mosaic.domain.MosaicsMetaDataResponse;
import io.nem.client.mosaic.domain.Namespace;
import io.nem.client.mosaic.domain.NamespacesMetaDataResponse;

public interface MosaicClient {
    NamespacesMetaDataResponse namespaces(Long id, Integer pageSize);
    Namespace namespace(String id);
    MosaicsMetaDataResponse mosaics(String namespaceId, Long id, Integer pageSize);
}

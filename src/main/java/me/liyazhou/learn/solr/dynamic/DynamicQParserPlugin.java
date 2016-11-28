package me.liyazhou.learn.solr.dynamic;

import org.apache.solr.common.params.SolrParams;
import org.apache.solr.common.util.NamedList;
import org.apache.solr.request.SolrQueryRequest;
import org.apache.solr.search.QParser;
import org.apache.solr.search.QParserPlugin;

/**
 * Created by liyazhou on 2016/11/27.
 */
public class DynamicQParserPlugin extends QParserPlugin {
    @Override
    public QParser createParser(String qstr, SolrParams localParams, SolrParams params, SolrQueryRequest req) {
        return new DynamicQParser(qstr, localParams, params, req);
    }

    public void init(NamedList args) {

    }
}

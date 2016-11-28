package me.liyazhou.learn.solr.dynamic;

import org.apache.lucene.search.Query;
import org.apache.solr.common.params.DisMaxParams;
import org.apache.solr.common.params.SolrParams;
import org.apache.solr.parser.QueryParser;
import org.apache.solr.request.SolrQueryRequest;
import org.apache.solr.search.QParser;
import org.apache.solr.search.SolrQueryParser;
import org.apache.solr.search.SyntaxError;
import org.apache.solr.util.SolrPluginUtils;

import java.util.Map;

/**
 * Created by liyazhou on 2016/11/27.
 */
public class DynamicQParser extends QParser {
    private SolrQueryParser lparser;
    private SolrParams solrParams;
    private Map<String, Float> queryFields;

    public DynamicQParser(String qstr, SolrParams localParams, SolrParams params, SolrQueryRequest req) {
        super(qstr, localParams, params, req);
    }

    @Override
    public Query parse() throws SyntaxError {
        SolrParams localParams = getLocalParams();
        SolrParams params = getParams();
        solrParams = SolrParams.wrapDefaults(localParams, params);
        queryFields = SolrPluginUtils.parseFieldBoosts(solrParams.getParams(DisMaxParams.QF));
        if (0 == queryFields.size()) {
            queryFields.put(req.getSchema().getDefaultSearchFieldName(), 1.0f);
        }

        String qstr = getString();
        String defaultField = getParam("df");
        if (defaultField == null) {
            defaultField = getReq().getSchema().getDefaultSearchFieldName();
        }

        this.lparser = new DynamicQueryParser(this, defaultField, queryFields);
        String opParam = getParam("q.op");
        if (opParam == null) {
            this.lparser.setDefaultOperator("AND".equals(opParam) ? QueryParser.Operator.AND : QueryParser.Operator.OR);
        } else {
            QueryParser.Operator operator = QueryParser.Operator.valueOf(getReq().getSchema().getQueryParserDefaultOperator());
            this.lparser.setDefaultOperator(operator == null ? QueryParser.Operator.OR : operator);
        }
        return this.lparser.parse(qstr);


    }
}

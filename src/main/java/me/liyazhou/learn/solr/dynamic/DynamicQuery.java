package me.liyazhou.learn.solr.dynamic;

import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.BooleanWeight;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Weight;
import org.apache.solr.common.params.SolrParams;

import java.io.IOException;

/**
 * Created by liyazhou on 2016/11/27.
 * //
 */
//public class DynamicQuery extends SolrQuery {
public class DynamicQuery extends BooleanQuery {
    //    private SolrParams params;
//
//    public DynamicQuery() {
//        super();
//    }
//
//    public DynamicQuery(SolrParams params, boolean disableCoord) {
//        super(disableCoord);
//        this.params = params;
//    }
//
    private SolrParams params;

    public DynamicQuery() {
        super();
    }

    public DynamicQuery(SolrParams params, boolean disableCoord) {
        super(disableCoord);
        this.params = params;
    }

    @Override
    public Weight createWeight(IndexSearcher searcher, boolean needsScores) throws IOException {
        return super.createWeight(searcher, needsScores);
    }

    class MultiWeight extends BooleanWeight {
        private SolrParams solrParams;
        private String[] factor;

        MultiWeight(BooleanQuery query, IndexSearcher searcher, boolean needsScores, boolean disableCoord) throws IOException {
            super(query, searcher, needsScores, disableCoord);
        }
//        public MultiWeight(BooleanQuery query, IndexSearcher searcher, boolean needsScores, boolean disableCoord) throws IOException {
//            super(query, searcher, needsScores, disableCoord);
//        }

    }
    //    public class MultiWeight extends BooleanWeight {
//        private SolrParams params;
//        private String[] factor;
//
//        public MultiWeight(SolrParams params, Searcher searcher, boolean disableCoord) throws IOException {
//            super(searcher, disableCoord);
//            this.params = params;
//        } //最重要的一步，修改公式得分
//
//        @Override
//        public Scorer scorer(IndexReader reader, boolean scoreDocsInOrder, boolean topScorer) throws IOException {
//            String[] tempFactor = FieldCache.DEFAULT.getStrings(reader, Constant.FIELD_FACTOR);
//            if (tempFactor != null && tempFactor.length != 0) {
//                factor = tempFactor.clone();
//            }
//            List<Scorer> required = new ArrayList<Scorer>();
//            List<Scorer> prohibited = new ArrayList<Scorer>();
//            List<Scorer> optional = new ArrayList<Scorer>();
//            Iterator<BooleanClause> cIter = clauses().iterator();
//            for (Weight w : weights) {
//                BooleanClause c = cIter.next();
//                Scorer subScorer = w.scorer(reader, true, false);
//                if (subScorer == null) {
//                    if (c.isRequired()) {
//                        return null;
//                    }
//                } else if (c.isRequired()) {
//                    required.add(subScorer);
//                } else if (c.isProhibited()) {
//                    prohibited.add(subScorer);
//                } else {
//                    optional.add(subScorer);
//                }
//            } // Check if we can return a BooleanScorer
//            if (!scoreDocsInOrder && topScorer && required.size() == 0) {
//                return new DynamicScorer(this, isCoordDisabled(), similarity, minNrShouldMatch, optional, prohibited, maxCoord);
//            }
//            if (required.size() == 0 && optional.size() == 0) { // no required and optional clauses.
//                return null;
//            } else if (optional.size() < minNrShouldMatch) { // either >1 req scorer, or there are 0 req scorers and at least 1 // optional scorer. Therefore if there are not enough optional scorers // no documents will be matched by the query
//                return null;
//            } // Return a BooleanScorer2
//            return new DynamicScorer2(this, isCoordDisabled(), similarity, minNrShouldMatch, required, prohibited, optional, maxCoord);
//        }
//
//        public String[] getFactor() {
//            return factor;
//        }
//
//        public SolrParams getParams() {
//            return params;
//        }
//    }
//
//    public Weight createWeight(Searcher searcher) throws IOException {
//        return new MultiWeight(params, searcher, isCoordDisabled());
//    }
}
//
//<requestHandler name="standard" class="solr.StandardRequestHandler" default="true">
//<lst name="defaults">
//<str name="defType">dynamic</str>
//<str name="qf">title^20 content^1</str>
//</lst>
//</requestHandler>
//<queryParser name="dynamic" class="cn.wxdl.extension.solr.search.DynamicQParserPlugin"/>

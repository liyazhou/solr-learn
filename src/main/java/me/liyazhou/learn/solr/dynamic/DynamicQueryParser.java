package me.liyazhou.learn.solr.dynamic;

import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.Query;
import org.apache.solr.search.QParser;
import org.apache.solr.search.SolrQueryParser;
import org.apache.solr.search.SyntaxError;

import java.util.List;
import java.util.Map;

/**
 * Created by liyazhou on 2016/11/27.
 */
public class DynamicQueryParser extends SolrQueryParser {

    private Map<String, Float> queryFields;

    public DynamicQueryParser(QParser parser, String defaultField) {
        super(parser, defaultField);
    }


//    private Map<String, Float> queryFields;

    //
//    public DynamicQueryParser(IndexSchema schema, String defaultField) {
//        super(schema, defaultField);
//    }
//
//    public DynamicQueryParser(QParser parser, String defaultField, Analyzer analyzer) {
//        super(parser, defaultField, analyzer);
//    }
//
//    public DynamicQueryParser(QParser parser, String defaultField) {
//        super(parser, defaultField);
//    }
//
//    public DynamicQueryParser(QParser parser, String defaultField, Map<String, Float> queryFields) {
//        super(parser, defaultField);
//        this.queryFields = queryFields;
//    } //将BooleanQuery替换为自定义DynamicQuery
//
    public DynamicQueryParser(QParser parser, String defaultField, Map<String, Float> queryFields) {
        super(parser, defaultField);
        this.queryFields = queryFields;
    }

//    protected Query getBooleanQuery(List<BooleanClause> clauses, boolean disableCoord) throws ParseException {
//        if (clauses.size() == 0) {
//            return null; // all clause words were filtered away by the analyzer.
//        }
//        DynamicQuery query = new DynamicQuery(parser.getParams(), disableCoord);
//        for (final BooleanClause clause : clauses) {
//            query.add(clause);
//        }
//        return query;
////    }

    @Override
    protected Query getBooleanQuery(List<BooleanClause> clauses, boolean disableCoord) throws SyntaxError {
        if (clauses.size() == 0) {
            return null;
        }
        DynamicQuery query = new DynamicQuery(parser.getParams(), disableCoord);
        for (final BooleanClause clause : clauses) {
            query.add(clause);
        }
        return query;
    }

//protected Query getFieldQuery(String field, String queryText, boolean quoted) throws ParseException {
//    String myField = field == null ? this.defaultField : field;
//    Float boots = queryFields.get(field);
//    if (myField != null) {
//        FieldType ft = this.schema.getField(myField).getType();
//        if ((ft instanceof TextField)) {
//            try {
//                Analyzer analyzer = ft.getQueryAnalyzer() == null ? ft.getAnalyzer() : ft.getQueryAnalyzer();
//                if (analyzer != null) {
//                    BooleanQuery bq = new BooleanQuery();
//                    TokenStream ts = analyzer.tokenStream(field, new StringReader(queryText));
//                    int endOffset = 0;
//                    while (ts.incrementToken()) {
//                        CharTermAttribute ta = (CharTermAttribute) ts.getAttribute(CharTermAttribute.class);
//                        OffsetAttribute oa = (OffsetAttribute) ts.getAttribute(OffsetAttribute.class);
//                        TermQuery termQuery = new TermQuery(new Term(myField, ta.toString()));
//                        if (boots != null) {
//                            termQuery.setBoost(boots);
//                        }
//                        if (oa.startOffset() >= endOffset) {
//                            bq.add(termQuery, BooleanClause.Occur.SHOULD);
//                            endOffset = oa.endOffset();
//                        } else {
//                            bq.add(termQuery, BooleanClause.Occur.SHOULD);
//                        }
//                    }
//                    return bq;
//                }
//            } catch (Exception e) {
//                throw new ParseException(e.getMessage());
//            }
//        }
//    }
//    return super.getFieldQuery(field, queryText, quoted);
//}

}

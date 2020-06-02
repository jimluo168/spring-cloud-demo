//package com.spring.cloud.demo.common.web.feign;
//
//import feign.RequestTemplate;
//import feign.codec.EncodeException;
//import feign.codec.Encoder;
//
//import java.lang.reflect.Type;
//
///**
// * TODO 简要说明
// *
// * @author luojm
// * @date 2020/6/2 14:40
// */
//public class PageableQueryEncoder implements Encoder {
//
//    private final Encoder delegate;
//
//    PageableQueryEncoder(Encoder delegate){
//        this.delegate = delegate;
//    }
//
//    @Override
//    public void encode(Object object, Type bodyType, RequestTemplate template) throws EncodeException {
//        if(object instanceof Pageable){
//            Pageable pageable = (Pageable)object;
//            template.query("page", pageable.getPageNumber() + "");
//            template.query("size", pageable.getPageSize() + "");
//
//            if(pageable.getSort() != null) {
//                Collection<String> existingSorts = template.queries().get("sort");
//                List<String> sortQueries  = existingSorts != null ? new ArrayList<>(existingSorts) : new ArrayList<>();
//                for (Sort.Order order : pageable.getSort()) {
//                    sortQueries.add(order.getProperty() + "," + order.getDirection());
//                }
//                template.query("sort", sortQueries);
//            }
//
//        }else{
//            delegate.encode(object, bodyType, template);
//        }
//    }
//}

package cn.mauth.crm.util.common;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public final class PageUtil {

    private final static int SIZE;
    private final static Sort SORT;

    static {
       SIZE=10;
       SORT=Sort.by(Sort.Direction.ASC,"id");
    }

    public static PageRequest getPageable(int page,int size,Sort sort){
        size=size>0?size:SIZE;

        page=page-1;

        page=page>0?page:0;

        sort=sort==null?SORT:sort;

        return PageRequest.of(page, size, sort);
    }

    public static Pageable getPageable(int page,int size){
        return getPageable(page, size, SORT);
    }

    public static Pageable getPageable(Pageable pageable){

        return getPageable(pageable.getPageNumber(),pageable.getPageSize(),pageable.getSort());
    }

    public static Pageable getPageable(Pageable pageParm, Sort sort){

        return getPageable(pageParm.getPageNumber(),pageParm.getPageNumber(),sort);
    }


    public static String like(String data){

        StringBuffer sb=new StringBuffer();

        sb.append("%");

        sb.append(data);

        sb.append("%");

        return sb.toString();
    }
}

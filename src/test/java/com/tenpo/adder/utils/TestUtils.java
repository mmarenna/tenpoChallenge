package com.tenpo.adder.utils;

import com.tenpo.adder.record.model.Record;
import org.assertj.core.util.DateUtil;

import java.util.Arrays;
import java.util.List;

public class TestUtils {

    public final static List<Record> PAGINATED_RECORDS_PAGE0_SIZE2 = Arrays.asList(new Record(1L,DateUtil.now(), "uri1" , "response1"),
            new Record(2L,DateUtil.now(), "uri2" , "response2"));

}

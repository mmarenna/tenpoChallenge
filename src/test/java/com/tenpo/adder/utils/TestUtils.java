package com.tenpo.adder.utils;

import com.tenpo.adder.history.model.History;
import org.assertj.core.util.DateUtil;

import java.util.Arrays;
import java.util.List;

public class TestUtils {

    public final static List<History> PAGINATED_HISTORIES_PAGE0_SIZE2 = Arrays.asList(new History(1L,DateUtil.now(), "uri1" , "response1"),
            new History(2L,DateUtil.now(), "uri2" , "response2"));

}

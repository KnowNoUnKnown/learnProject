package com.oracle.xing.functional;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

public class ContextPool {

    private List<Context> contexts;

    public ContextPool(Context... contexts) {
        if(null != contexts && contexts.length > 0){
            this.contexts = Lists.newArrayList(Arrays.asList(contexts));
        }
    }

    public List<Context> getContexts() {
        return contexts;
    }

    public void addContext(Context context){
        contexts.add(context);
    }
}
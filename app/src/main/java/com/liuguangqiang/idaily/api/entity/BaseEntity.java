package com.liuguangqiang.idaily.api.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by Eric on 15/6/9.
 */
public class BaseEntity implements MultiItemEntity {

    @Override
    public int getItemType() {
        return 0;
    }
}

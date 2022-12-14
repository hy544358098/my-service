package com.crc.business.independent.model.vo.result;

import com.crc.business.independent.model.entity.TDictionariesClassification;
import com.crc.business.independent.model.entity.TDictionariesDetailed;
import lombok.Data;

import java.util.List;

/**
 * 字典总数据返回值实体
 *
 * @author xiaoming
 */
@Data
public class DictionariesNewVo {

  private TDictionariesClassification tDictionariesClassification;
  private List<TDictionariesDetailed> list;
}

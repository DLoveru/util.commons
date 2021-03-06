package pers.hai.util.commons.consts;

/**
 * 计算机存储单位的枚举
 *
 * Create Date: 2015-12-14
 * Last Modify: 2016-05-26
 * 
 * @author Q-WHai
 * @see <a href="https://github.com/qwhai">https://github.com/qwhai</a>
 */
public enum MemoryUnit {
    
    /** 位 */
    Bit("位"),
    
    /** 字节 */
    Byte("字节"),
    
    /** 千字节 */
    KB("千字节"),
    
    /** 兆字节 */
    MB("兆字节"),
    
    /** 千兆字节 */
    GB("千兆字节"),
    
    /** 太字节 */
    TB("太字节"),
    
    /** PB */
    PB("PB"),
    
    /** EB */
    EB("EB"),
    
    /** ZB */
    ZB("ZB"),
    
    /** YB */
    YB("YB"),
    
    /** NB */
    NB("NB"),
    
    /** DB */
    DB("DB");
    
    private String desc;
    
    MemoryUnit(String _desc) {
        desc = _desc;
    }
    
    /**
     * 获得枚举值的描述
     * 
     * @return
     *      描述
     */
    public String getDesc() {
        return desc;
    }
}

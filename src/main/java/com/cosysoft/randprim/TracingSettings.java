package com.cosysoft.randprim;

/**
 * todo: description
 */
interface TracingSettings {
    void setWithoutLabelPrefix(String prefix);
    void setAllLabelsPrefix(String prefix);
    void setAllowLabelsOverride(boolean isAllow);
}

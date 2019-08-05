package io.hedwig.modules.engine.result;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.util.ArrayList;
import java.util.List;

public class OverallResult {
    @Excel(name = "期望值")
    private Object baseLine;
    @Excel(name="实际值")
    private Object testLine;
    @Excel(name = "差异")
    private List<DifferenceResult> result= new ArrayList<>();
    @Excel(name = "是否相同")
    private boolean isSame;

    public Object getBaseLine() {
        return baseLine;
    }

    public void setBaseLine(Object baseLine) {
        this.baseLine = baseLine;
    }

    public Object getTestLine() {
        return testLine;
    }

    public void setTestLine(Object testLine) {
        this.testLine = testLine;
    }


    public List<DifferenceResult> getResult() {
        return result;
    }

    public void setResult(List<DifferenceResult> result) {
        this.result = result;
    }


    public boolean isSame() {
        return isSame;
    }

    public void setSame(boolean same) {
        isSame = same;
    }
}

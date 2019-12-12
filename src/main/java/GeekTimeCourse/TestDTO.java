package GeekTimeCourse;

/**
 * @ClassName: TestDTO
 * @Description: 重写equals&HashCode方法
 * @Author: wufangmin
 * @Date: 2019/12/3 14:22
 * @Version: 1.0
 */
public class TestDTO {
    private String dtoName;

    private String version;

    private String purpose;


    public String getDtoName() {
        return dtoName;
    }

    public void setDtoName(String dtoName) {
        this.dtoName = dtoName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public TestDTO() {
    }

    public TestDTO(String dtoName, String version, String purpose) {
        this.dtoName = dtoName;
        this.version = version;
        this.purpose = purpose;
    }

    @Override
    public int hashCode() {
        int h = 0;
        if(null != this.getDtoName()){
            h ^= this.getDtoName().hashCode();
        }
        if(null != this.getVersion()){
            h ^= this.getVersion().hashCode();
        }
        if(null != this.getPurpose()){
            h ^= this.getPurpose().hashCode();
        }
        return h;
    }

    @Override
    public boolean equals(Object obj) {
        if(null == obj || !(obj instanceof  TestDTO)){
            return false;
        }

        if(this.hashCode() == obj.hashCode())
            return true;

        return false;
    }
}

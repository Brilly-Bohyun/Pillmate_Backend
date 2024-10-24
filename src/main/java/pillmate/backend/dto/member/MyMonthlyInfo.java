package pillmate.backend.dto.member;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MyMonthlyInfo {
    private String grade;
    private Integer takenDay;
    private Integer month;
    private Integer rate;
}

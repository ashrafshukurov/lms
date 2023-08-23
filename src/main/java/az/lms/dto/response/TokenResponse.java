/*
 *Created by Jaweed.Hajiyev
 *Date:21.08.23
 *TIME:12:41
 *Project name:LMS
 */

package az.lms.dto.response;

import lombok.Data;

@Data
public class TokenResponse {
    private String accessToken;
    private String refreshToken;
}

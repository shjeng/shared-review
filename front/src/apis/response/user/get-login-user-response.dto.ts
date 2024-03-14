import { User } from "../../../types/interface";
import ResponseDto from "../response.dto";

export default interface GetLoginUserResponseDto extends ResponseDto {
  userDto: User;
}

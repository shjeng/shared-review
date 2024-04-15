import CategorieList from "../../../types/interface/categorie-list.interface";
import ResponseDto from "../response.dto";

export default interface GetAdminCategorysResponseDto extends ResponseDto {
  adminCategorys: CategorieList[];
}

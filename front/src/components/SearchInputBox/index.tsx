import React, { useEffect, useRef, useState } from "react";
import "./style.css";
import { Category } from "../../types/interface";
import { getCategorysReqeust } from "../../apis";
import { GetCategorysResponseDto } from "../../apis/response/board";
import ResponseDto from "../../apis/response/response.dto";

// interface: Input Box 컴포넌트 Properties
interface Props {
  columns: { label: string; field: string }[];
}

const SearchInputBox = ({ columns }: Props) => {
  const [categoryDrop, setCategoryDrop] = useState(false);
  const searchInputRef = useRef<any>(null);
  const toggleDropdown = () => {
    setCategoryDrop(!categoryDrop);
  };
  const [category, setCategory] = useState<Category | undefined>();
  const onCategoryClick = (category: Category) => {
    setCategory(category);
  };

  const [categorys, setCategorys] = useState<Category[]>([]);

  useEffect(() => {
    getCategorysReqeust().then(getCategorysResponse);
  }, []);
  const getCategorysResponse = (
    responseBody: GetCategorysResponseDto | ResponseDto | null
  ) => {
    if (!responseBody) {
      alert("서버로부터 응답이 없습니다.");
      return;
    }
    const { code } = responseBody;
    if (code === "VF") alert("유효성 검사 실패");
    if (code === "DBE") alert("데이터베이스 오류");
    if (code !== "SU") {
      return;
    }
    const result = responseBody as GetCategorysResponseDto;
    setCategorys(result.categorys);
  };

  return (
    <>
      <div className="admin-categori-bottom-top">
        <div className="header-category">
          <div className="header-category-dropdown" ref={searchInputRef}>
            <div className="dropdown-box" onClick={toggleDropdown}>
              <div className="dropdown_text">검색</div>
              <div className="dropdown_icon"></div>
            </div>
            {categoryDrop && (
              <div className="dropdown-content">
                {columns.map(({ label, field }) => (
                  <div key={field} className="board-dropdown-content-item">
                    {label}
                  </div>
                ))}
              </div>
            )}
          </div>
        </div>

        <div className="admin-search">
          <input type="text" placeholder="검색어 입력" />
          <div className="admin-search-img"></div>
        </div>
      </div>
    </>
  );
};

export default SearchInputBox;
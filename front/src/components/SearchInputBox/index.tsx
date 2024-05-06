import React, { useEffect, useRef, useState } from "react";
import "./style.css";
import CategorieList from "../../types/interface/admin-categorie.interface";

// interface: Input Box 컴포넌트 Properties
interface Props {
  columns: { label: string; field: string }[];
  onSearch: (inputValue: string, searchValue: string) => void;
}

const SearchInputBox = ({ columns, onSearch }: Props) => {
  const [searchDrop, setSearchDrop] = useState(false);
  const [search, setSearch] = useState<string>("전체");
  const [searchValue, setSearchValue] = useState<string>("");

  const [dropdownValue, setDropdownValue] = useState<string>("");
  const [inputValue, setInputValue] = useState<string>("");

  const searchInputRef = useRef<any>(null);

  const toggleDropdown = () => {
    setSearchDrop(!searchDrop);
  };

  const onSearchClick = (label: string, field: string) => {
    setSearch(label);
    setSearchValue(field);
    console.log("field : ", JSON.stringify(field, null, 2));

    setSearchDrop(false);
  };

  const onCategorySearch = () => {
    // console.log("columns : ", JSON.stringify(columns, null, 2));

    // setDropdownValue(searchValue);

    const inputValue = searchInputRef.current?.value || "";
    setInputValue(inputValue);
    if (search !== "전체" && inputValue.length === 0) {
      alert("검색어를 입력해주세요.");
      return;
    }
    onSearch(inputValue, searchValue);
    console.log("search,  : ", JSON.stringify(search, null, 2));

    console.log("inputValue,  : ", JSON.stringify(inputValue, null, 2));
    console.log("searchValue,  : ", JSON.stringify(searchValue, null, 2));
  };

  return (
    <>
      <div className="admin-categori-bottom-top">
        <div className="header-category">
          <div className="header-category-dropdown">
            <div className="dropdown-box" onClick={toggleDropdown}>
              <div className="dropdown_text">{search}</div>
              <div className="dropdown_icon"></div>
            </div>
            {searchDrop && (
              <div className="dropdown-content">
                {columns.map(({ label, field }) => (
                  <div
                    key={field}
                    className="board-dropdown-content-item"
                    onClick={() => onSearchClick(label, field)}
                  >
                    {label}
                  </div>
                ))}
              </div>
            )}
          </div>
        </div>

        <div className="admin-search">
          <input type="text" placeholder="검색어 입력" ref={searchInputRef} />
          <div className="admin-search-img" onClick={onCategorySearch}></div>
        </div>
      </div>
    </>
  );
};

export default SearchInputBox;

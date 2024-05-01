import React, { useEffect, useRef, useState } from "react";
import "./style.css";

// interface: Input Box 컴포넌트 Properties
interface Props {
  columns: { label: string; field: string }[];
}

const SearchInputBox = ({ columns }: Props) => {
  const [searchDrop, setSearchDrop] = useState(false);
  const searchInputRef = useRef<any>(null);
  const toggleDropdown = () => {
    setSearchDrop(!searchDrop);
  };
  const [search, setSearch] = useState<string>("검색기준");

  const onSearchClick = (search: string) => {
    setSearch(search);
    setSearchDrop(false);
  };

  return (
    <>
      <div className="admin-categori-bottom-top">
        <div className="header-category">
          <div className="header-category-dropdown" ref={searchInputRef}>
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
                    onClick={() => onSearchClick(label)}
                  >
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

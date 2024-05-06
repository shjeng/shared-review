import SearchInputBox from "../../components/SearchInputBox";
import "./style.css";

const UserBoard = () => {
  const userDefinedColumns = [
    { label: "글번호", field: "id" },
    { label: "제목", field: "title" },
    { label: "카테고리", field: "category" },
    { label: "작성날짜", field: "writeDateTime" },
  ];
  return (
    <div>
      <div className="user-board-main-bottom-box">
        <div className="user-bottom-title">"{`**님`}" 게시글</div>
        {/* <SearchInputBox columns={userDefinedColumns} /> */}
        <div className="user-board-item-list">
          <div className="user-board-item-box">
            <div className="user-board-item-top">
              <div className="user-board-item-title">{`제목`}</div>
              <div className="user-board-item-comment">{`[0]`}</div>
            </div>
            <div className="user-board-item-bottom">
              <div className="user-board-item-category">{`카테고리`}</div>
              <div className="user-board-item-timeInfo">{`2024-04-30`}</div>
              <div className="user-board-item-nickname">{`testNick`}</div>
            </div>
          </div>

          <div className="user-board-item-box">
            <div className="user-board-item-top">
              <div className="user-board-item-title">{`제목`}</div>
              <div className="user-board-item-comment">{`[0]`}</div>
            </div>
            <div className="user-board-item-bottom">
              <div className="user-board-item-category">{`카테고리`}</div>
              <div className="user-board-item-timeInfo">{`2024-04-30`}</div>
              <div className="user-board-item-nickname">{`testNick`}</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default UserBoard;

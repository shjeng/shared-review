import "@toast-ui/editor/dist/toastui-editor.css";
import "./style.css";
import React, { useRef } from "react";
import { Editor } from "@toast-ui/react-editor";

const BoardWrite = () => {
  const test = () => {};

  const editorRef = useRef<Editor>(null);

  const onChange = () => {
    // 에디터 내용 가져옴
    const content = editorRef.current?.getInstance().getHTML();
  };
  const onSubmit = () => {
    const content = editorRef.current?.getInstance().getHTML();
    console.log(content);
  };
  return (
    <div id="board-write-wrap">
      <div className="board-write-top">
        <div className="board-title">게시물 작성</div>
        <div className="board-category">
          <div className="board-dropdown-box" onClick={test}>
            <div className="board-dropdown-text">카테고리</div>
            <div className="board-dropdown-icon"></div>
          </div>
          {false && (
            <div className="board-dropdown-content">
              <div className="board-dropdown-content-item" onClick={test}>
                2
              </div>
              <div className="board-dropdown-content-item" onClick={test}>
                oasidjf;oizsdjfo;zsdijzsd;foisjfd;szofdijzdf
              </div>
            </div>
          )}
        </div>
      </div>
      <div className="board-write-mid">
        <div className="board-main-left">
          <div className="board-input-title">
            <input type="text" />
          </div>
          <div className="editor_box">
            <Editor
              ref={editorRef}
              initialValue="hello react editor world!"
              previewStyle="vertical"
              height="600px"
              onChange={onChange}
              initialEditType="wysiwyg"
              useCommandShortcut={false}
            />
          </div>
          <div className="board-main">
            <div className="board-detail"></div>
            <div className="board-attach"></div>
          </div>
        </div>

        <div className="board-main-right">
          <div className="board-registered" onClick={onSubmit}>
            {"등록"}
          </div>
          <div className="board-attach-icon">1/10</div>
        </div>
      </div>

      <div className="board-write-bottom">
        <div className="board-bottom-tag"></div>
      </div>
    </div>
  );
};

export default BoardWrite;

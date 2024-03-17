import "@toast-ui/editor/dist/toastui-editor.css";
import "./style.css";
import React, { useRef } from "react";
import { Editor } from "@toast-ui/react-editor";

const BoardWrite = () => {
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
    <>
      <div>게시물 작성</div>
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
      <div onClick={onSubmit}>전송</div>
    </>
  );
};

export default BoardWrite;

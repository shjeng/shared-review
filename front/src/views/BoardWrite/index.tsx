import "@toast-ui/editor/dist/toastui-editor.css";
import "./style.css";
import React, {
  ChangeEvent,
  KeyboardEvent,
  useEffect,
  useRef,
  useState,
} from "react";
import { Editor } from "@toast-ui/react-editor";

const BoardWrite = () => {
  const titleRef = useRef<HTMLInputElement | null>(null);
  const tagRef = useRef<HTMLInputElement | null>(null);

  const [title, setTitle] = useState<string>("");
  const [categoryDrop, setCategoryDrop] = useState(false);
  const [categorys, setCategorys] = useState<string[]>([]);
  const [tag, setTag] = useState<string>("");
  const [tags, setTags] = useState<string[]>([]);
  const [contentHtml, setContentHtml] = useState<string>();
  const [contentMarkdouw, setContentMarkdouw] = useState<string>();
  const toggleDropdown = () => {
    setCategoryDrop(!categoryDrop);
  };

  const test = () => {};

  const editorRef = useRef<Editor>(null);
  const searchInputRef = useRef<any>(null);

  // 카테고리 드롭다운 박스 외부를 클릭했을 때 드롭다운을 닫는 기능
  const handleClickOutside = () => {
    if (categoryDrop) {
      setCategoryDrop(false);
    }
  };

  // const handleClickOutside = (e: MouseEvent) => {
  //   if (categoryDrop && !searchInputRef.current.contains(e.target as Node)) {
  //     setCategoryDrop(false);
  //   }
  // };

  // useEffect(() => {
  //   document.addEventListener("mousedown", handleClickOutside);
  //   return () => {
  //     document.removeEventListener("mousedown", handleClickOutside);
  //   };
  // }, [categoryDrop]);

  const onTitleChange = (event: ChangeEvent<HTMLInputElement>) => {
    const { value } = event.target;
    setTitle(value);
  };
  const onChange = () => {
    // 에디터 내용 content
    setContentHtml(editorRef.current?.getInstance().getHTML());
    setContentMarkdouw(editorRef.current?.getInstance().getMarkdown());
  };

  const onSubmit = () => {
    const content = editorRef.current?.getInstance().getHTML();
    console.log(content);
  };

  // event handler:  Tag
  const tagKeyDown = (event: KeyboardEvent<HTMLInputElement>) => {
    if ((event.key === "Enter" || event.key === "Tab") && tag.length !== 0) {
      const newTags = [...tags];
      newTags.push(tag);
      setTags(newTags);
    }
  };
  const onTagChange = (event: ChangeEvent<HTMLInputElement>) => {
    const { value } = event.target;
    setTag(value);
    tagRef.current?.focus();
  };
  return (
    <div id="board-write-wrap" onClick={handleClickOutside}>
      <div className="board-write-top">
        <div className="board-title">게시물 작성</div>

        <div className="function-line">
          <div className="board-category" ref={searchInputRef}>
            <div className="board-dropdown-box" onClick={toggleDropdown}>
              <div className="board-dropdown-text">카테고리</div>
              <div className="board-dropdown-icon"></div>
            </div>
            {categoryDrop && (
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

          <div className="board-registered" onClick={onSubmit}>
            {"등록"}
          </div>
        </div>
      </div>
      <div className="board-write-mid">
        <div className="board-main-left">
          <div className="board-input-title">
            <input
              type="text"
              placeholder="제목을 입력해주세요."
              ref={titleRef}
              onChange={onTitleChange}
            />
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
      </div>

      <div className="board-write-bottom">
        <div className="board-bottom-tag">
          {tags.map((t) => (
            <div className="tag">#{t}</div>
          ))}
          <input
            type="text"
            placeholder="태그를 입력해주세요"
            onKeyDown={tagKeyDown}
            onChange={onTagChange}
            ref={tagRef}
          />
        </div>
      </div>
    </div>
  );
};

export default BoardWrite;

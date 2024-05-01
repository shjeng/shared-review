import { useEffect, useState } from "react";

// countPerPage : 한페이지당 몇개 보여줄지
const usePagination = (countPerPage: number) => {
  //  state: 전체 item 수    //
  const [countPerItem,setCountPerItem] = useState<number>(10);
  const [currentPage, setCurrentPage] = useState<number>(1);
  const [totalCount, setTotalCount] = useState<number>(0);
  const [section, setSection] = useState<number>(0);
  const [totalSection, setTotalSection] = useState<number>(0);
  const [start, setStart] = useState<number>(1);
  const [end, setEnd] = useState<number>(1);
  const [pageList, setPageList] = useState<number[]>([]);
  //  state: 현재 페이지 번호 상태   //
  // 지금 몇번째 페이지에 있는지에 대한걸 저장할 상태

  // 섹션 1부터,
  const set = () => {
    const totalPage = totalCount / countPerItem;
    setSection(Math.floor(currentPage / countPerPage) + 1);
    const endSection = totalCount / countPerPage === 0 ? totalCount / countPerPage - 1 : Math.floor( totalCount / countPerPage );
    setTotalSection(endSection);
    const startNumber = section * countPerPage + 1;
    setStart(startNumber);
    const endNumber = totalPage > section * countPerPage ? section * countPerPage : totalPage;
    setEnd(endNumber);
    const pages = Array.from({ length: end - start + 1}, (_, index) => start + index);
    setPageList(pages);
  }

  useEffect(() => {
    set();
  }, [currentPage]);

  return {
    setCurrentPage, setTotalCount, setCountPerItem, countPerPage, countPerItem, start, end,totalSection, pageList, currentPage, section, setSection
  }

}


export default usePagination;

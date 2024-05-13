import {create} from "zustand";
import {Category, User} from "../types/interface";

interface BoardSearchStore {
    searchWord: string | null;
    setSearchWord: (searchWord: string) => void;
    resetSearchWord: () => void;

    categoryId: string | bigint | null;
    setCategoryId: (categoryId: string | bigint) => void;
    resetCategoryId: () => void;
}

const useBoardSearchStore = create<BoardSearchStore>((set) => ({
    searchWord: null,
    setSearchWord: (searchWord: string) => set((state) => ({...state, searchWord})),
    resetSearchWord: () => set((state) => ({...state, searchWord: null})),

    categoryId: null,
    setCategoryId: (categoryId: string | bigint) => set((state) => ({...state, categoryId})),
    resetCategoryId: () => set((state) => ({...state, state: null})),

}));

export default useBoardSearchStore;

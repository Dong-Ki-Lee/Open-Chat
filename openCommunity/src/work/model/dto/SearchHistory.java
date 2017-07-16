package work.model.dto;

import java.io.Serializable;

public class SearchHistory implements Serializable {

		public int memberNo;
		public String searchWord;
		/**
		 * 
		 */
		public SearchHistory() {
			super();
			// TODO Auto-generated constructor stub
		}
		/**
		 * @param memberNo
		 * @param searchWord
		 */
		public SearchHistory(int memberNo, String searchWord) {
			super();
			this.memberNo = memberNo;
			this.searchWord = searchWord;
		}
		/**
		 * @return the memberNo
		 */
		public int getMemberNo() {
			return memberNo;
		}
		/**
		 * @param memberNo the memberNo to set
		 */
		public void setMemberNo(int memberNo) {
			this.memberNo = memberNo;
		}
		/**
		 * @return the searchWord
		 */
		public String getSearchWord() {
			return searchWord;
		}
		/**
		 * @param searchWord the searchWord to set
		 */
		public void setSearchWord(String searchWord) {
			this.searchWord = searchWord;
		}
		
		
}

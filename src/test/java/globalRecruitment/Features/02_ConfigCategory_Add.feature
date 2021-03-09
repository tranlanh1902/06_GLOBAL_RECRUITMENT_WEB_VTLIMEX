 
Feature: Thêm cấu hình danh mục liên quan 

Background: Mở trang Thêm cấu hình danh mục thành công 
	Given DangNhap: mở trang Đăng nhập 
	Then DangNhap: kiểm tra trang Đăng nhập mở thành công với button Login được hiển thị 
	And DangNhap: nhập dữ liệu vào các trường với dữ liệu 
		| Code/Email | Password   | 
		| "274018"   | "123456a@" |
	And DangNhap: nhấn button Đăng nhập  
	Then TrangChu: kiểm tra login thành công với acc "Đỗ Văn Đô" được hiển thị 
	When TrangChu: nhấn menu icon "Quản lý cấu hình danh mục liên quan" 
	Then QLCauHinhDanhMuc-TimKiem: kiểm tra mở trang Tìm kiếm cấu hình thành công với title "DANH SÁCH CẤU HÌNH DANH MỤC LIÊN QUAN" 
	When QLCauHinhDanhMuc-TìmKiem: nhấn button Thêm 
	Then QLCauHinhDanhMuc-Them: kiểm tra mở trang Thêm cấu hình thành công với title "THÊM MỚI CẤU HÌNH" 

@ConfigCategory_Add
Scenario Outline: 01_[QL Cấu hình danh mục - Thêm] Kiểm tra Thêm cấu hình thành công 
	Given QLCauHinhDanhMuc-Them: nhập dữ liệu màn hình Thêm cấu hình với các dữ liệu sau 
		| Name   | NameEnglish   | Type   | CategoryRelevant1   | CategoryRelevant2   | 
		| <name> | <nameEnglish> | <type> | <categoryRelevant1> | <categoryRelevant2> |
	When QLCauHinhDanhMuc-Them: nhấn button Lưu 
	And QLCauHinhDanhMuc-Them: nhấn button OK xác nhận lưu 
	Then QLCauHinhDanhMuc-TimKiem: kiểm tra thêm thành công với toast message hiển thị với nội dung "Thao tác thực hiện thành công" 
	And QLCauHinhDanhMuc-Them: kiểm tra insert thành công DB bảng CAT_GROUP 1 row với dữ liệu đã thêm 
	And QLCauHinhDanhMuc-Them: kiểm tra insert thành công DB bảng CATEGORY_GROUP 2 row với dữ liệu đã thêm 
	When QLCauHinhDanhMuc-Them: lấy dữ liệu column code của Cấu hình danh mục vừa thêm trong bảng CAT_GROUP 
	
	Examples: Thông tin Thêm cấu hình danh mục thành công 
		| case                                                                                                                    | name                                                                                                   | nameEnglish                                                                                            | type       | categoryRelevant1  | categoryRelevant2 | 
#		| Kiểm tra thêm mới thành công khi nhập các trường textbox = maxlength (Tên cấu hình = 100, Tên cấu hình(Tiếng anh) = 100 | "1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111112" | "1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111112" | "Ngôn ngữ" | "Tiếng Nhật"       | "Tiếng Việt"      | 
		| Kiểm tra thêm mới thành công khi nhập các trường bắt buộc khi text box chứa space đầu cuối                              | " Ngôn ngữ1 "                                                                                          | " Ngôn ngữ english1 "                                                                                  | "Ngôn ngữ" | "Tiếng Bồ Đào Nha" | "Tiếng Hàn"       | 
#		| Kiểm tra thêm mới thành công khi nhập các trường bắt buộc                                                               | "Ngôn ngữ2"                                                                                            | "Ngôn ngữ english2"                                                                                    | "Ngôn ngữ" | "Chinese"          | "Thái Lan"        | 
		
		
Scenario Outline:02_[QL Cấu hình danh mục - Thêm] Thêm mới cấu hình không thành công 
	Given QLCauHinhDanhMuc-Them: nhập dữ liệu màn hình Thêm cấu hình với các dữ liệu lỗi sau 
		| Field   | Case   | Name   | NameEnglish   | Type   | CategoryRelevant1   | CategoryRelevant2   | 
		| <field> | <case> | <name> | <nameEnglish> | <type> | <categoryRelevant1> | <categoryRelevant2> | 
	When QLCauHinhDanhMuc-Them: nhấn button Lưu nếu typeError là special thì bỏ qua 
		| TypeError   | 
		| <typeError> |
	And QLCauHinhDanhMuc-Them: nhấn button OK xác nhận lưu nếu typeError là unique 
		| TypeError   | 
		| <typeError> | 
	Then QLCauHinhDanhMuc-Them: kiểm tra message error hiển thị 
		| Field   | MessageError   | TypeError   |
		| <field> | <messageError> | <typeError> |
		
	Examples: Thông tin Thêm mới cấu hình không thành công 
		| field                    | case                           | name        | nameEnglish     | type       | categoryRelevant1 | categoryRelevant2 | messageError                                 | typeError | 
		| Tên cấu hình             | Kiểm tra bắt buộc              | ""          | "Ngôn ngữ eng3" | "Ngôn ngữ" | "Tiếng Nga"       | "Tiếng Pháp"      | Trường bắt buộc                              | required  | 
		| Tên cấu hình             | Kiểm tra duy nhất              | "báo cáo"   | "Ngôn ngữ eng3" | "Ngôn ngữ" | "Tiếng Nga"       | "Tiếng Pháp"      | Tên cấu hình đã tồn tại                      | unique    | 
		| Tên cấu hình             | Kiểm tra nhập ký tự đặc biệt   | "@#$"       | ""              | ""         | ""                | ""                | Tên cấu hình không chứa ký tự đặc biệt       | special   | 
		| Tên cấu hình (Tiếng Anh  | Kiểm tra bắt buộc              | "Ngôn ngữ3" | ""              | "Ngôn ngữ" | "Tiếng Nga"       | "Tiếng Pháp"      | Trường bắt buộc                              | required  | 
		| Tên cấu hình (Tiếng Anh) | Kiểm tra duy nhất              | "Ngôn ngữ3" | "báo cáo eng"   | "Ngôn ngữ" | "Tiếng Nga"       | "Tiếng Pháp"      | Tên cấu hình tiếng anh đã tồn tại            | unique    | 
		| Tên cấu hình (Tiếng Anh) | Kiểm tra nhập ký tự đặc biệt   | ""          | "@#$"           | ""         | ""                | ""                | Tên cấu hình không chứa ký tự đặc biệt       | special   | 
		| Loại danh mục            | Kiểm tra bắt buộc              | "Ngôn ngữ3" | "Ngôn ngữ eng3" | ""         | ""                | ""                | Trường bắt buộc                              | required  | 
		| Danh mục liên quan       | Kiểm tra bắt buộc              | "Ngôn ngữ3" | "Ngôn ngữ eng3" | "Ngôn ngữ" | ""                | ""                | Vui lòng chọn ít nhất hai danh mục liên quan |           | 
		| Danh mục liên quan       | Kiểm tra số lượng danh mục < 2 | "Ngôn ngữ3" | "Ngôn ngữ eng3" | "Ngôn ngữ" | "Tiếng Nga"       | ""                | Vui lòng chọn ít nhất hai danh mục liên quan |           | 
		
		
Scenario Outline: 03_[QL Cấu hình danh mục - Thêm] Kiểm tra chức năng autocomplete thành công 
	Given QLCauHinhDanhMuc-Them: nhập dữ liệu trường Loại danh mục là tiền điều kiện nếu field là Danh mục liên quan 
		| Type   | Field   | 
		| <type> | <field> |
	And QLCauHinhDanhMuc-Them: nhập autocomplete với dữ liệu 
		| Field   | Case   | Autocomplete   | 
		| <field> | <case> | <autocomplete> |
	And QLCauHinhDanhMuc-Them: lấy danh sách kết quả tìm kiếm trên UI trả về 
		| Field   | 
		| <field> |
	And QLCauHinhDanhMuc-Them: lấy danh sách kết quả tìm kiếm DB trả về 
		| Field   | Autocomplete   | Type   | 
		| <field> | <autocomplete> | <type> | 
	Then QLCauHinhDanhMuc-Them: kiểm tra danh sách kết quả tìm kiếm trên UI trả về bằng danh sách kết quả tìm kiếm DB trả về 
	
	Examples: Thông tin kiểm tra chức năng autocomplete 
		| field              | case                                                  | autocomplete      | type         | 
		| Loại danh mục      | Tìm kiếm không ra kết quả                             | "@#$"             |              | 
		| Loại danh mục      | Tìm kiếm mặc định                                     | ""                |              | 
		| Loại danh mục      | Tìm kiếm nhập chuỗi ký tự có space đầu cuối           | "Loại tuyển dụng" |              | 
		| Loại danh mục      | Tìm kiếm tương đối và không phân biệt hoa thường      | "TUYỂN DỤNG"      |              | 
		| Loại danh mục      | Tìm kiếm không phân biệt tiếng việt có dấu, không dấu | "Loai tuyen dung" |              | 
		| Danh mục liên quan | Tìm kiếm mặc định                                     | ""                | "Ngôn ngữ"   | 
		| Danh mục liên quan | Tìm kiếm không ra kết quả                             | "@#$"             | "Ngôn ngữ"   | 
		| Danh mục liên quan | Tìm kiếm nhập chuỗi ký tự có space đầu cuối           | " Tiếng Pháp "    | "Ngôn ngữ"   | 
		| Danh mục liên quan | Tìm kiếm tương đối và không phân biệt hoa thường      | "PHÁP"            | "Ngôn ngữ"   | 
		| Danh mục liên quan | Tìm kiếm không phân biệt tiếng việt có dấu, không dấu | "Tieng Phap"      | "Ngôn ngữ"   |
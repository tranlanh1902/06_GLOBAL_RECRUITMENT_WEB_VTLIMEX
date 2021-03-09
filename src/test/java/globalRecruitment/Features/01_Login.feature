@Login 
Feature: Đăng nhập 
Scenario Outline: 01_[Đăng nhập] Đăng nhập thành công
	Given DangNhap: mở trang Đăng nhập
	Then DangNhap: kiểm tra trang Đăng nhập mở thành công với button Login được hiển thị 
	When DangNhap: nhập dữ liệu vào các trường với dữ liệu 
		| Code/Email   | Password   | 
		| <code/Email> | <password> | 
	And DangNhap: nhấn button Đăng nhập
	Then TrangChu: kiểm tra login thành công với acc "Đỗ Văn Đô" được hiển thị 
	
	Examples: Thông tin 
		| GroupTC              | codeTC | nameTC               | code/Email | password   | 
		| Đăng nhập thành công | TC01   | Đăng nhập thành công | "274018"   | "123456a@" | 
		
Scenario Outline: 02_[Đăng nhập] Đăng nhập không thành công 
	Given DangNhap: mở trang Đăng nhập 
	Then DangNhap: kiểm tra trang Đăng nhập mở thành công với button Login được hiển thị 
	When DangNhap: nhập dữ liệu vào các trường với dữ liệu 
		| Code/Email   | Password   | 
		| <code/Email> | <password> | 
	And DangNhap: nhấn button Đăng nhập 
	Then DangNhap: kiểm tra hiển thị các message lỗi 
		| MessageError   | 
		| <messageError> | 
		
	Examples: Thông tin 
		| GroupTC                    | codeTC | Field         | nameTC                          | code/Email | password   | messageError                              | 
		| Đăng nhập không thành công | TC02   | Tên đăng nhập | Nhập trTên đăng nhập không đúng | "274010"   | "123456a@" | "Tài khoản hoặc mật khẩu không chính xác" | 
		| Đăng nhập không thành công | TC03   | Mật khẩu      | Nhập trường Mật khẩu không đúng | "274018"   | "123457a@" | "Tài khoản hoặc mật khẩu không chính xác" | 
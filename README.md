# UserManagement 

"Hệ thống quản lý User"
- Signin / Signout
  + Email - Password : Java web Token
  + Facebook : Spring Social
  + Google : Spring Social
  + Linkedin : Spring Social
  + SMS : SMS API (Twilio + Nexmo)
  + Token : Send Gmail + code
  
# Document Spring Security 

+ Authentication (xác thực) : là tiến trình thiết lập một principal. Principal có thể hiểu là một người, hoặc một thiết bị,
  hoặc một hệ thống nào đó có thể thực hiện một hành động trong ứng dụng của bạn.

+ Authorization (phân quyền) hay Access-control : là tiến trình quyết định xem một principal có được phép thực hiện một
  hành động trong ứng dụng của bạn hay không. Trước khi diễn tiến tới Authorization, principal cần phải được thiết lập bởi Authentication.

+ SecurityContext : là interface cốt lõi của Spring Security, lưu trữ tất cả các chi tiết liên quan đến bảo mật trong ứng dụng.
  Khi chúng ta kích hoạt Spring Security trong ứng dụng thì SecurityContext cũng sẽ được kích hoạt theo.
  chúng ta sẽ không truy cập trực tiếp vào SecurityContext, thay vào đó sẽ sử dụng lớp

+ SecurityContextHolder. Lớp này lưu trữ security context hiện tại của ứng dụng, bao gồm chi tiết của principal đang tương
  tác với ứng dụng. Spring Security sẽ dùng một đối tượng Authentication để biểu diễn thông tin này
  ( Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal(); )

+ UserDetails là một interface cốt lõi của Spring Security. Nó đại diện cho một principal nhưng theo một cách mở rộng và cụ thể hơn. 
  Vậy UserDetails cung cấp cho ta những thông tin gì? UserDetails bao gồm các method sau:

    . getAuthorities(): trả về danh sách các quyền của người dùng
    . getPassword(): trả về password đã dùng trong qúa trình xác thực
    . getUsername(): trả về username đã dùng trong qúa trình xác thực
    . isAccountNonExpired(): trả về true nếu tài khoản của người dùng chưa hết hạn
    . isAccountNonLocked(): trả về true nếu người dùng chưa bị khóa
    . isCredentialsNonExpired(): trả về true nếu chứng thực (mật khẩu) của người dùng chưa hết hạn
    . isEnabled(): trả về true nếu người dùng đã được kích hoạt


+ Filter là đối tượng cho phép can thiệp vào dữ liệu từ request và response trước khi dữ liệu này đến điểm cuối là Web Container

+ Vòng đời của một Filter giống như vòng đời của một Servlet: khởi tạo, thi hành và tiêu hủy. Việc khởi tạo 1 filter chỉ được thực hiện 1 lần khi Web Application lần đầu gọi Filter để sử dụng,
  phần thi hành của 1 filter được gọi khi có resquest hay response cần dùng filter đó, khi Web Application hoàn toàn không còn dùng filter nữa thì vòng đời của filter sẽ kết thúc

+ Filter chains là đối tượng giúp cho Filter có nhiều công dụng hơn như Servlet. Như đồ hình bên dưới cho thấyFilter có thể làm chức năng như Servlet dù đó không phải là công dụng ban đầu khi Filter được thiết kế ra.
 Như giới thiệu phía trên, hàm doFilter có 3 tham số, request, response như Servlet, FilterChain là tham số mới hơn so với Servlet, nó công dụng giúp Filter truyền resquest hay response qua nhiều Filter khác nhau

+ 

# Json Web Token 
+ JSON Web Token (JWT) là 1 tiêu chuẩn mở (RFC 7519) định nghĩa cách thức truyền tin an toàn giữa các thành viên bằng 1 đối tượng JSON.
  Thông tin này có thể được xác thực và đánh dấu tin cậy nhờ vào "chữ ký" của nó. Phần chữ ký của JWT sẽ được mã hóa lại bằng HMAC hoặc RSA.	

+ JWT trên bao gồm 3 phần: (phân cách nhau bởi dấu chấm )

    . Header (2 phần chính)
	    - typ - Loại token (mặc định là JWT - Thông tin này cho biết đây là một Token JWT)
      - alg - Thuật toán đã dùng để mã hóa (HMAC SHA256 - HS256 hoặc RSA).
    
	. Payload   : nơi chứa nội dung thông tin
    
	. Signature : Phần chữ ký được tạo bằng cách kết hợp 2 phần Header + Payload, rồi mã hóa nó lại bằng 1 giải thuật encode nào đó,
                càng phức tạp thì càng tốt, ví dụ như HMAC SHA-256
				  
  


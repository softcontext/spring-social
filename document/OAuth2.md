# What is OpenID, OAuth2 and Google Sign In?

http://dba-presents.com/index.php/other/my-thoughts/125-what-is-openid-oauth2-and-google-sign-in

`신뢰 당사자(Relying Party)는 보안 소프트웨어 응용 프로그램에 대한 액세스를 제공하는 서버를 지칭하는 데 사용되는 컴퓨터 용어입니다. 여기서 신뢰 당사는 컨슈머를 의미합니다.`

그런 화면을 본 적이 있습니까? Facebook, Google, Microsoft 버튼이있는 로그인 페이지이지만 페이지가 해당 회사에 속하지 않으십니까? 그렇다면 대부분 OpenID 또는 OAuth2 프로토콜을 사용하여 소셜 계정을 사용하여 해당 웹 사이트에 로그인 할 수 있습니다.

나는 그것을 단순화하지 않고 가능한 한 간단하게 어떻게 작동하는지 당신에게 빨리 설명 할 것입니다.

## Traditional authentication

우리가 그것에 뛰어 들기 전에 전통적인 인증 개념 위에서 잠시 멈춰 봅시다. 우리가 아는 대부분의 시스템은 이런 방식으로 구축됩니다.

<img src="./img/traditionalAuth.png"/>

사용자는 인터넷 브라우저를 사용하여 웹 사이트에 액세스합니다. 웹 사이트가 인증을 요구하면 사용자는 사용자 데이터베이스에서 확인 된 사용자 이름과 암호를 제공합니다. 데이터베이스는 웹 사이트 소유자가 소유하며 해당 시스템의 일부입니다. 간단하고 잘 작동하지만 사용자 입장에서는 계정이있는 여러 웹 사이트가 있습니다.

<img src="./img/traditionalAuthMultiSys.png"/>

각 웹 사이트에는 자체 계정 데이터베이스가 있습니다. 사용자는 모든 웹 사이트에서 별도의 계정을 가지고 있습니다. 보안상의 이유로 다른 암호를 사용하는 것이 더 좋으며 정기적으로 모든 암호를 변경하십시오. 그것은 사용자에게 힘든 작업입니다.

모든 웹 사이트에 공통 사용자 데이터베이스가있는 경우 사용자가 더 쉬울 것입니다. 그는 그들 모두를 위해 단 하나의 계정 만 가질 것입니다.

<img src="./img/commonUsersDb.png"/>

물론 웹 사이트 중 어느 것도 다른 사용자 자격 증명을 사용하지 못하도록해야합니다. 맞습니까? 트위터가 Gmail에서 내 이메일을 읽는 것을 원하지 않지만 같은 계정을 사용하여 두 애플리케이션에 모두 액세스 할 수 있다면 좋을 것입니다.

## OpenID concept

다행히도이를 수행 할 수있는 방법이 있습니다.OpenID라고합니다.중앙 집중식 서버가없는 프로토콜입니다. 열려 있기 때문에 Google, Facebook, Twitter, Microsoft와 같은 많은 ID 프로바이더가 있습니다. 사용자는 ID 프로바이더를 사용하여 신뢰 당사자 웹 사이트에 대해 인증 할 수 있습니다.웹 마스터가 웹 사이트에서 여러 프로바이더를 구성 할 수 있기 때문에 사용자가 선택할 수 있습니다.ID 프로바이더의 페이지에서 인증이 진행됨에 따라 사용자 비밀번호가 신뢰 당사자에게 전송되지 않도록하는 것이 중요합니다.OpenID는 실제로 좋은 시작이지만 세계는 조금 더 나아 갔고 널리 OAuth를 사용합니다.개념은 매우 유사하므로 지금부터 OAuth에 중점을 둘 것입니다.

## So what is OAuth?

이것은 OpenID를 기반으로하는 프로토콜이지만 인증뿐만 아니라 권한확인을 허용하기 위해 조금 확장되었습니다. 그것은 신뢰 당사자가 사용자의 이름을 알 수 있고 이메일 주소 등을 얻을 수 있음을 의미합니다. 물론 사용자 동의가 있어야합니다. 사용자가 동의하지 않으면 아무것도 발생하지 않습니다.OpenID와 OAuth의 차이점을 이해해 봅시다. OpenID는 사용자가 자신이 주장하는 ID의 소유자임을 확인하는 데 사용됩니다. 예 : ID는 Google Plus에서 계정 번호 = 123456이 될 수 있습니다. 신뢰 당사자는 여전히 Google Plus에서 해당 계정의 이메일 주소를 알지 못합니다. 그건 OpenID입니다.

신뢰 당사자는 Google Plus에서 해당 정보를 쿼리 할 수있는 권한이 있으면 사용자 이메일 주소를 알 수 있습니다. 인증은 OAuth 프로토콜을 사용하여 처리됩니다.OAuth 프로토콜의 목적이 이해되면서 어떻게 작동하는지 자세히 설명하겠습니다. OAuth2가이 프로토콜의 가장 최신 버전이기 때문에 여기에 초점을 맞출 것입니다.

## OAuth2 protocol

OAuth2가 지원하는 단 하나의 긍정적 인 시나리오를 제시 하겠지만 몇 가지가 더 있습니다. 이 개념을 이해하기에 충분해야합니다.

<img src="./img/OAuth2_algorithm.png"/>

이 프로세스는 인터넷 브라우저로 웹 사이트에 들어가려고하는 사용자로부터 시작됩니다.
1. 그것은 "웹 사이트보기"요청을 신뢰 당사자에게 보냅니다.
2. 웹 사이트는 사용자가 누구인지 알 필요가 있으므로 "인증을 받아야합니다"라고 응답하고 사용자는 ID 제공자를 선택할 수 있습니다.
3. 그는 웹 사이트에서 지원하는 Google을 선택합니다. 이는 Google 로그인이 웹 개발자에 의해 구성되었음을 의미합니다.

설정의 일부는 식별자와 비밀 키를 웹 사이트에 할당하는 것이 었습니다. 둘 다 웹 사이트와 Google간에 공유됩니다. ID는 비밀로 유지되지 않습니다. 키는 이 경우 ID 제공자 인 Google외에 다른 누구와도 공유해서는 안되는 열쇠입니다. 해당 ID와 비밀 키를 알고 있으면 해당 웹 사이트 또는 소유자임을 의미합니다. ID는 은행 계좌에 로그인 한 것과 같습니다. 고유하지만 도용으로 공유하면 여전히 돈을받을 수 없습니다. 비밀 키는 은행 계좌의 비밀번호와 같습니다. 공개 한 경우 누구나 자신이 귀하이고 귀하의 계좌에서 돈을 송금 할 수 있습니다.

4. 시나리오로 돌아가서 사용자는 Google에 로그인하기로 결정했습니다. 웹 응용 프로그램은 웹 사이트 ID가 포함 된 리디렉션 지침으로 응답합니다. 응답의 일부는 비밀 키로 암호화됩니다.
5. 브라우저는 웹 사이트 ID와 암호화 된 부분을 제공하는 Google 로그인 페이지로 리디렉션하여 Google이 요청의 출처를 알 수 있도록합니다.
6. 사용자가 성공적으로 로그인하면 Google은 사용자에게 신뢰 당사자와 공유 할 데이터 범위에 동의 할 것을 요청합니다.
7. 이 시나리오에서는 사용자가 동의하여 Google에서 한 번 인증 코드를 생성하고
8. 이를 비밀 키로 암호화 한 다음 브라우저로 리디렉션 요청과 함께 다시 전송하도록합니다.
9. 브라우저는 암호화 된 인증 코드를 전달하는 신뢰 당사자 웹 사이트로 이동합니다. 신뢰 당사자는 비밀 키를 사용하여 암호를 해독하고 인증 코드를 읽습니다. 승인 코드는 Google 및 신뢰 당사자 만이 알 수 있습니다. 심지어 사용자는 비밀 키를 모르므로 해독 할 수 없습니다.

10. 인증 코드를 통해 신뢰 당사자는 Google에 액세스 토큰을 요청할 수 있습니다.
12. 또한 액세스 토큰을 사용하여 Google로부터 사용자 데이터를 요청할 수 있습니다. Google은 사용자가 동의 한 토큰 및 데이터 범위를 확인하고 신뢰 당사자에게 요청 된 데이터를 제공합니다.
14. 이제 신뢰 당사자 웹 사이트는 사용자 신원을 알고 있으며 사용자 신원을 확인할 수 있습니다.

OAuth2가 일반적인 경우에 사용되는 방식입니다.

## Should I use OAuth2?

여전히 OpenID / OAuth2를 사용하기를 주저하는 경우 사용자가 시스템에 새 계정을 만들 필요가 없다는 점에 유의하십시오. 사람들은 많은 계정을 만드는 것을 좋아하지 않습니다. 우리는 새로운 암호를 기억하려고 노력하는 것보다 우리가 가지고있는 암호를 사용하기를 더 원합니다.

Google이 자신의 신임장을 안전하게 보관한다고 신뢰하면 신뢰 당사자가 내 비밀번호를 볼 수 없기 때문에 동일한 신뢰 수준을 필요로하지 않습니다.

웹 응용 프로그램 개발자 로서도 이점이 있습니다. 커스텀 로그인 시스템을 만드는 것은 잘 알려진 분야이지만 여전히 많은 코딩이 필요합니다. 데이터베이스의 암호는 해시되어야하며 만료 날짜가 있어야합니다. 비밀번호 재설정 및 변경 기능을 구현해야합니다. 또한 사용자가 암호를 설정할 수 없도록해야합니다. 너무 단순하다면 거절해야합니다. ID 제공자와 통합하여 피할 수있는 많은 작업입니다.

이메일 주소, 성 및 이름과 같은 PII 데이터를 수집하고 처리 할 필요가 없으므로 많은 경우 법적 측면에서 OAuth2를 사용하는 것이 보다 간단합니다.

OAuth2 및 Google 로그인에 관심이 있다면 Spring Boot에서 Spring 로그인 또는 Google Spring 로그인없이 Spring을 사용하는 방법을 볼 수 있습니다. Google에서 프로필 세부 정보를 읽는 방법을 볼 수도 있습니다.
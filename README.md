# Uramens API

## 목표
<pre>
AWS ECR,ECS,RDS,FARGATE,S3,VPC,Load Balancer, GITHUB ACTIONS 를 활용하여 빌드/배포 자동화 서비스 구축 및 DDD 학습
</pre>
## 구성도

## DEV
----------------------------------------
- build gradle

```
./gradlew build
```

- docker image build

```
docker build -t uramens-api:latest .
```

- docker run

```
docker run -d -p 9090:9090 --rm --name uramens-api uramens-api:latest
```

- docker 컨테이너 중지
```
docker stop <컨테이너 이름 혹은 아이디>
```
- docker 컨테이너 중지
```
docker start <컨테이너 이름 혹은 아이디>
```
- docker 컨테이너 중지
```
docker restart <컨테이너 이름 혹은 아이디>
```
- docker 컨테이너 중지
```
docker attach <컨테이너 이름 혹은 아이디>
```
- docker 컨테이너 삭제
```
docker rm -f <container id>
```

----------------------------------------
## EC2 만 사용 할 경
----------------------------------------
* update & docker 설치
<pre>
ssh -i uramens.cer ubuntu@3.36.72.176
sudo apt update
sudo apt install apt-transport-https ca-certificates curl software-properties-common
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu bionic stable"
sudo apt update
apt-cache policy docker-ce
sudo apt install docker-ce
</pre>
----------------------------------------
* docker sudo 비활성화

<pre>
sudo usermod -aG docker ubuntu
exit
logout
</pre>
------------------------------------------
* awscli 설치

<pre>
sudo apt install awscli
</pre>
------------------------------------------
* aws 설정

<pre>
aws configure
-- IAM 에서 생성한 access_id, key 값, region 값 등을 추가 한다.
-- default output format 은 선택하지 않으면 기본값이 json 
</pre>

## EC2 API 수동 실행

------------------------------------------
* ECR 로그인

<pre>
aws --region "ap-northeast-2" ecr get-login-password
- 응답 받은 암호화된 패스워드 문자열을 복사한다
docker login --username AWS -p <암호화된 패스워드> <ERC 도커 이미지 주소>
</pre>

------------------------------------------
* ECR 도커 이미지 가져오기

<pre>
docker pull <ERC 도커 이미지 주소>
</pre>

------------------------------------------
* 도커 실행
<pre>
docker run -d -p 9090:9090 --rm --name uramens-api <이미지아이디>
</pre>



package com.app.gameface.webServices;


import com.app.gameface.fragment.*;

import java.io.File;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by ajit on 7/3/2017.
 */

public interface Response
{

    @FormUrlEncoded
    @POST("login.json")
    Call<LoginResponse> login_params(@Field("phone") String phone, @Field("country_code")
            String country_code, @Field("password") String password, @Field("device_id") String device_id);


    @FormUrlEncoded
    @POST("resend_otp.json")
    Call<ResendOtpResponse> resend_otp_params(@Field("user_id") String user_id);


    @FormUrlEncoded
    @POST("register.json")
    Call<SignUpResponse> signup_params(@Field("username") String username,
                                       @Field("email") String email,
                                       @Field("password") String password,
                                       @Field("phone") String phone,
                                       @Field("country_code") String country_code,
                                       @Field("device_id") String device_id);


    @FormUrlEncoded
    @POST("check_verify_status.json")
    Call<CheckVerifyStatus> check_verify_status(@Field("user_id") String user_id,
                                       @Field("verify_status") String verify_status
                                       );



    @FormUrlEncoded
    @POST("facebook_login.json")
    Call<FbLoginResponse> fb_login_Response(@Field("email") String email,
                                            @Field("username") String username,
                                            @Field("fb_id") String fb_id,
                                            @Field("device_id") String device_id,
                                            @Field("image") String image
    );

    @FormUrlEncoded
    @POST("update_fb_phone_no.json")
    Call<UpdateFbPhoneNo> update_fb_no_response(@Field("user_id") String user_id,
                                            @Field("phone") String phone,
                                            @Field("country_code") String country_code
    );

    @FormUrlEncoded
    @POST("forgot_password.json")
    Call<ForgotPasswordResponse> forgot_password_response(@Field("email") String email);

    @FormUrlEncoded
    @POST("get_contacts.json")
    Call<GetContactResponse> get_contacts_response(@Field("user_id") String user_id,
                                                   @Field("token") String token,
                                                   @Field("contact") String contact);


    @FormUrlEncoded
    @POST("send_invitation.json")
    Call<SendInvitationResponse> send_invitation_response(@Field("user_id") String user_id,
                                                   @Field("token") String token,
                                                   @Field("contact") String contact);

    @FormUrlEncoded
    @POST("get_ads_categories.json")
    Call<GetAdsCatogriesResponse> get_ads_catogries_response(@Field("user_id") String user_id,
                                                          @Field("token") String token);

    @FormUrlEncoded
    @POST("get_ads.json")
    Call<GetAdsResponse> get_ads_response(@Field("user_id") String user_id,
                                          @Field("token") String token,
                                          @Field("category_id") String category_id);

    @FormUrlEncoded
    @POST("add_group.json")
    Call<AddGroupResponse> add_group_params(@Field("user_id") String user_id, @Field("token")
            String token, @Field("group_name") String group_name, @Field("group_type") String group_type
            , @Field("team_name") String team_name, @Field("sport") String sport
            , @Field("snack_schedule") String snack_schedule, @Field("games") String games
            , @Field("organization_pin") String organization_pin, @Field("organization_name") String organization_name
            , @Field("age_group") String age_group, @Field("address") String address
            , @Field("contacts") String contacts, @Field("clipboard") String clipboard
    );


    @FormUrlEncoded
    @POST("get_group_list.json")
    Call<GetGroupList> get_group_list_response(@Field("user_id") String user_id,
                                          @Field("token") String token);

    @FormUrlEncoded
    @POST("delete_group.json")
    Call<DeleteGroupResponse> delete_group_response(@Field("user_id") String user_id,
                                               @Field("token") String token,
                                             @Field("group_id") String group_id
                                             );


    @FormUrlEncoded
    @POST("get_group_members.json")
    Call<GetGroupMembersResponse> get_group_members_response(@Field("user_id") String user_id,
                                                    @Field("token") String token,
                                                    @Field("group_id") String group_id
    );


    @FormUrlEncoded
    @POST("send_group_message.json")
    Call<SendTextMessageResponse> send_text_msg_response(@Field("user_id") String user_id,
                                                             @Field("token") String token,
                                                             @Field("group_id") String group_id
                                                             ,@Field("type") String type
                                                             ,@Field("message") String message
    );

    @FormUrlEncoded
    @POST("get_group_chat.json")
    Call<GetChatHistoryResponse> get_chat_response(@Field("user_id") String user_id,
                                                         @Field("token") String token,
                                                         @Field("group_id") String group_id
                                             ,@Field("page_no") String page_no);


    @FormUrlEncoded
    @POST("change_group_name.json")
    Call<ChangeGroupNameRespnse> change_gname_response(@Field("user_id") String user_id,
                                                   @Field("token") String token,
                                                   @Field("group_id") String group_id,
                                                       @Field("group_name") String group_name);


    @Multipart
    @POST("change_group_image.json")
    Call<ChangeGroupImage> change_gimage_response(@Part("user_id") String user_id,
                                                  @Part("token") String token,
                                                  @Part("group_id") String group_id,
                                                  @Part MultipartBody.Part image);


    @FormUrlEncoded
    @POST("change_your_name.json")
    Call<ChangeUserName> change_name_response(@Field("user_id") String user_id,
                                                  @Field("token") String token,
                                                  @Field("name") String name
                                                 );

    @FormUrlEncoded
    @POST("change_team_age_group.json")
    Call<ChangeAgeGroup> change_age_group_response(@Field("user_id") String user_id,
                                              @Field("token") String token,
                                              @Field("age_group") String age_group,
                                               @Field("group_id") String group_id

    );

    @FormUrlEncoded
    @POST("assign_coach_captain.json")
    Call<AssignCaptainResponse> assign_coach_response(@Field("user_id") String user_id,
                                                       @Field("token") String token,
                                                       @Field("group_id") String group_id,
                                                    @Field("coach_cap_id") String coach_cap_id

    );

    @FormUrlEncoded
    @POST("get_schedule_from_TSG_group.json")
    Call<TeamSportSchduleResponse> team_sport_schdule_response(@Field("user_id") String user_id,
                                                      @Field("token") String token,
                                                      @Field("group_id") String group_id );


    @FormUrlEncoded
    @POST("get_matchups.json")
    Call<FootballFantasyMatchupsResponse> football_fanatsy_matchups_response(@Field("user_id") String user_id,
                                                                                        @Field("token") String token,
                                                                                        @Field("group_id") String group_id );
}
